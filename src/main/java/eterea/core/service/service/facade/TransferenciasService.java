package eterea.core.service.service.facade;

import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.TransferenciaDto;
import eterea.core.service.kotlin.model.dto.TransferenciaWrapperDto;
import eterea.core.service.model.CuentaMovimiento;
import eterea.core.service.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferenciasService {

    private final TransferenciaService transferenciaService;
    private final ValorMovimientoService valorMovimientoService;
    private final CuentaMovimientoService cuentaMovimientoService;
    private final CuentaService cuentaService;
    private final ValorService valorService;
    private final CuentaMovimientoFirmaService cuentaMovimientoFirmaService;

    public TransferenciaWrapperDto findByUnique(Integer negocioIdDesde, Integer negocioIdHasta, Long numeroTransferencia) {
        log.debug("Processing TransferenciasService.findByUnique");
        var transferenciaDto = TransferenciaDto.Companion.fromEntity(transferenciaService.findByUnique(negocioIdDesde, negocioIdHasta, numeroTransferencia));
        var transferenciaWrapper = new TransferenciaWrapperDto.Builder()
                .transferencia(transferenciaDto)
                .valorMovimientos(valorMovimientoService.findAllByContable(transferenciaDto.getFecha(), transferenciaDto.getOrdenContable()))
                .cuentaMovimientos(cuentaMovimientoService.findAllByContable(transferenciaDto.getFecha(), transferenciaDto.getOrdenContable()))
                .build();
        log.debug("TransferenciaWrapper -> {}", transferenciaWrapper.jsonify());
        return transferenciaWrapper;
    }

    @Transactional
    public String regenerate(TransferenciaWrapperDto transferenciaWrapper) {
        log.debug("Processing TransferenciasService.regenerate");
        var transferenciaExterna = transferenciaWrapper.getTransferencia();
        assert transferenciaExterna != null;
        log.debug("Transferencia Externa -> {}", transferenciaExterna.toEntity().jsonify());
        var transferenciaLocal = transferenciaService.findByUnique(Objects.requireNonNull(transferenciaWrapper.getTransferencia()).getNegocioIdDesde(), transferenciaWrapper.getTransferencia().getNegocioIdHasta(), transferenciaWrapper.getTransferencia().getNumeroTransferencia());
        log.debug("Transferencia Local -> {}", transferenciaLocal.jsonify());
        var fechaContableLocal = transferenciaLocal.getFecha();
        var ordenContableLocal = transferenciaLocal.getOrdenContable();
        var cuentaMovimientoFirmaLocal = cuentaMovimientoFirmaService.findByAsiento(fechaContableLocal, ordenContableLocal);
        log.debug("Asiento Contable Local -> {} / {}", fechaContableLocal, ordenContableLocal);
        // Busco cuenta puente
        var cuentaPuente = Objects.requireNonNull(Objects.requireNonNull(transferenciaWrapper.getTransferencia().getComprobante()).getCuenta()).getCuentaMaestro();
        log.debug("Cuenta Maestro -> {}", cuentaPuente);
        var cuentaPuenteLocal = cuentaService.findByCuentaMaestro(cuentaPuente);
        log.debug("Cuenta Puente Local -> {}", cuentaPuenteLocal.jsonify());
        var valorLocals = valorService.findAll().stream().collect(Collectors.toMap(Valor::getValorId, valor -> valor));
        // Eliminar valores asociados
        valorMovimientoService.deleteAllByContable(fechaContableLocal, ordenContableLocal);
        log.debug("Valores eliminados");
        // Eliminar imputaciones asociadas
        cuentaMovimientoService.deleteAllByContable(fechaContableLocal, ordenContableLocal);
        log.debug("Imputaciones eliminadas");
        // Crea valores análogos
        List<ValorMovimiento> valorMovimientoLocales = new ArrayList<>();
        for (var valorMovimientoExterno : Objects.requireNonNull(transferenciaWrapper.getValorMovimientos())) {
            assert valorMovimientoExterno != null;
            var valorLocal = valorLocals.get(valorMovimientoExterno.getValorId());
            var valorMovimientoLocal = new ValorMovimiento.Builder()
                    .valorMovimientoId(null)
                    .negocioId(transferenciaExterna.getNegocioIdHasta())
                    .valorId(valorMovimientoExterno.getValorId())
                    .proveedorId(0L)
                    .clienteId(0L)
                    .fechaEmision(valorMovimientoExterno.getFechaEmision())
                    .fechaVencimiento(valorMovimientoExterno.getFechaVencimiento())
                    .comprobanteId(valorMovimientoExterno.getComprobanteId())
                    .numeroComprobante(valorMovimientoExterno.getNumeroComprobante())
                    .importe(valorMovimientoExterno.getImporte().multiply(new BigDecimal(-1)))
                    .numeroCuenta(valorLocal.getNumeroCuenta())
                    .fechaContable(fechaContableLocal)
                    .ordenContable(ordenContableLocal)
                    .proveedorMovimientoId(valorMovimientoExterno.getProveedorMovimientoId())
                    .clienteMovimientoId(valorMovimientoExterno.getClienteMovimientoId())
                    .titular(valorMovimientoExterno.getTitular())
                    .banco(valorMovimientoExterno.getBanco())
                    .receptor(valorMovimientoExterno.getReceptor())
                    .estadoId(valorMovimientoExterno.getEstadoId())
                    .fechaEntrega(valorMovimientoExterno.getFechaEntrega())
                    .tanda(valorMovimientoExterno.getTanda())
                    .tandaIndex(valorMovimientoExterno.getTandaIndex())
                    .cierreCajaId(0L)
                    .nivel(0)
                    .observaciones(valorMovimientoExterno.getObservaciones())
                    .build();
            valorMovimientoLocales.add(valorMovimientoLocal);
        }
        // Crea imputaciones análogas
        var item = 0;
        List<CuentaMovimiento> cuentaMovimientoLocales = new ArrayList<>();
        for (var valorMovimientoLocal : valorMovimientoLocales) {
            // imputación haber
            var cuentaMovimiento = CuentaMovimiento.builder()
                    .cuentaMovimientoId(null)
                    .fecha(fechaContableLocal)
                    .orden(ordenContableLocal)
                    .item(++item)
                    .debita((byte) 0)
                    .negocioId(transferenciaExterna.getNegocioIdHasta())
                    .numeroCuenta(cuentaPuenteLocal.getNumeroCuenta())
                    .comprobanteId(valorMovimientoLocal.getComprobanteId())
                    .concepto("Tra: " + 
                        String.format("%02d", transferenciaExterna.getNegocioIdDesde()) + " - " + 
                        String.format("%02d", transferenciaExterna.getNegocioIdHasta()) + " - " + 
                        String.format("%06d", transferenciaExterna.getNumeroTransferencia()))
                    .importe(valorMovimientoLocal.getImporte())
                    .subrubroId(0L)
                    .proveedorId(0L)
                    .clienteId(0L)
                    .cierreCajaId(0L)
                    .nivel(0)
                    .firma(cuentaMovimientoFirmaLocal.getCuentaMovimientoFirmaId())
                    .tipoAsientoId(0)
                    .articuloMovimientoId(0L)
                    .ejercicioId(null)
                    .inflacion((byte) 0)
                    .build();
            cuentaMovimientoLocales.add(cuentaMovimiento);
            // imputación debe
            cuentaMovimiento = CuentaMovimiento.builder()
                    .cuentaMovimientoId(null)
                    .fecha(fechaContableLocal)
                    .orden(ordenContableLocal)
                    .item(++item)
                    .debita((byte) 1)
                    .negocioId(transferenciaExterna.getNegocioIdHasta())
                    .numeroCuenta(valorLocals.get(valorMovimientoLocal.getValorId()).getNumeroCuenta())
                    .comprobanteId(valorMovimientoLocal.getComprobanteId())
                    .concepto("Tra: " +
                            String.format("%02d", transferenciaExterna.getNegocioIdDesde()) + " - " +
                            String.format("%02d", transferenciaExterna.getNegocioIdHasta()) + " - " +
                            String.format("%06d", transferenciaExterna.getNumeroTransferencia()))
                    .importe(valorMovimientoLocal.getImporte())
                    .subrubroId(0L)
                    .proveedorId(0L)
                    .clienteId(0L)
                    .cierreCajaId(0L)
                    .nivel(0)
                    .firma(cuentaMovimientoFirmaLocal.getCuentaMovimientoFirmaId())
                    .tipoAsientoId(0)
                    .articuloMovimientoId(0L)
                    .ejercicioId(null)
                    .inflacion((byte) 0)
                    .build();
            cuentaMovimientoLocales.add(cuentaMovimiento);
        }
        // Graba cuentaMovimientoLocales
        cuentaMovimientoService.saveAll(cuentaMovimientoLocales);
        log.debug("CuentaMovimientos grabados");
        // Graba valorMovimientoLocales
        valorMovimientoService.saveAll(valorMovimientoLocales);
        log.debug("Valores grabados");
        return "Reenviado";
    }

}

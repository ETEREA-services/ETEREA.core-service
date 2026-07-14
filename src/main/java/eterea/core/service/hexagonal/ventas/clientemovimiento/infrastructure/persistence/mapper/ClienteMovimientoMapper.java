package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.stock.articulomovimiento.application.service.ArticuloMovimientoService;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.entity.ClienteMovimientoEntity;
import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper.ComprobanteMapper;
import eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper.ComprobanteDtoMapper;
import eterea.core.service.hexagonal.empresa.infrastructure.persistence.mapper.EmpresaMapper;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ClienteMovimientoInvoiceDataResponse;
import eterea.core.service.hexagonal.invoicedata.infrastructure.mapper.ArticuloMovimientoMapper;
import eterea.core.service.hexagonal.invoicedata.infrastructure.mapper.ClienteMapper;
import eterea.core.service.hexagonal.invoicedata.infrastructure.mapper.MonedaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ClienteMovimientoMapper {

    private final ComprobanteMapper comprobanteMapper;
    private final EmpresaMapper empresaMapper;
    private final ClienteMapper clienteMapper;
    private final MonedaMapper monedaMapper;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final ArticuloMovimientoMapper articuloMovimientoMapper;
    private final ComprobanteDtoMapper comprobanteDtoMapper;

    public ClienteMovimiento toDomain(ClienteMovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return ClienteMovimiento.builder()
                .clienteMovimientoId(entity.getClienteMovimientoId())
                .comprobanteId(entity.getComprobanteId())
                .puntoVenta(entity.getPuntoVenta())
                .numeroComprobante(entity.getNumeroComprobante())
                .fechaComprobante(entity.getFechaComprobante())
                .clienteId(entity.getClienteId())
                .legajoId(entity.getLegajoId())
                .fechaVencimiento(entity.getFechaVencimiento())
                .negocioId(entity.getNegocioId())
                .empresaId(entity.getEmpresaId())
                .importe(entity.getImporte())
                .cancelado(entity.getCancelado())
                .neto(entity.getNeto())
                .netoCancelado(entity.getNetoCancelado())
                .montoIva(entity.getMontoIva())
                .montoIvaRni(entity.getMontoIvaRni())
                .reintegroTurista(entity.getReintegroTurista())
                .fechaContable(entity.getFechaContable())
                .ordenContable(entity.getOrdenContable())
                .recibo(entity.getRecibo())
                .asignado(entity.getAsignado())
                .anulada(entity.getAnulada())
                .decreto104316(entity.getDecreto104316())
                .letraComprobante(entity.getLetraComprobante())
                .montoExento(entity.getMontoExento())
                .reservaId(entity.getReservaId())
                .montoCuentaCorriente(entity.getMontoCuentaCorriente())
                .cierreCajaId(entity.getCierreCajaId())
                .cierreRestaurantId(entity.getCierreRestaurantId())
                .nivel(entity.getNivel())
                .eliminar(entity.getEliminar())
                .cuentaCorriente(entity.getCuentaCorriente())
                .letras(entity.getLetras())
                .cae(entity.getCae())
                .caeVencimiento(entity.getCaeVencimiento())
                .codigoBarras(entity.getCodigoBarras())
                .participacion(entity.getParticipacion())
                .monedaId(entity.getMonedaId())
                .cotizacion(entity.getCotizacion())
                .observaciones(entity.getObservaciones())
                .trackUuid(entity.getTrackUuid())
                .clienteMovimientoIdSlave(entity.getClienteMovimientoIdSlave())
                .comprobante(comprobanteMapper.toDomain(entity.getComprobante()))
                .cliente(entity.getCliente())
                .moneda(entity.getMoneda())
                .empresa(empresaMapper.toDomainModel(entity.getEmpresa()))
                .build();
    }

    public ClienteMovimientoEntity toEntity(ClienteMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return ClienteMovimientoEntity.builder()
                .clienteMovimientoId(domain.getClienteMovimientoId())
                .comprobanteId(domain.getComprobanteId())
                .puntoVenta(domain.getPuntoVenta() != null ? domain.getPuntoVenta() : 0)
                .numeroComprobante(domain.getNumeroComprobante() != null ? domain.getNumeroComprobante() : 0L)
                .fechaComprobante(domain.getFechaComprobante())
                .clienteId(domain.getClienteId() != null ? domain.getClienteId() : 0L)
                .legajoId(domain.getLegajoId())
                .fechaVencimiento(domain.getFechaVencimiento())
                .negocioId(domain.getNegocioId() != null ? domain.getNegocioId() : 0)
                .empresaId(domain.getEmpresaId() != null ? domain.getEmpresaId() : 0)
                .importe(domain.getImporte() != null ? domain.getImporte() : BigDecimal.ZERO)
                .cancelado(domain.getCancelado() != null ? domain.getCancelado() : BigDecimal.ZERO)
                .neto(domain.getNeto() != null ? domain.getNeto() : BigDecimal.ZERO)
                .netoCancelado(domain.getNetoCancelado() != null ? domain.getNetoCancelado() : BigDecimal.ZERO)
                .montoIva(domain.getMontoIva() != null ? domain.getMontoIva() : BigDecimal.ZERO)
                .montoIvaRni(domain.getMontoIvaRni() != null ? domain.getMontoIvaRni() : BigDecimal.ZERO)
                .reintegroTurista(domain.getReintegroTurista() != null ? domain.getReintegroTurista() : BigDecimal.ZERO)
                .fechaContable(domain.getFechaContable())
                .ordenContable(domain.getOrdenContable() != null ? domain.getOrdenContable() : 0)
                .recibo(domain.getRecibo() != null ? domain.getRecibo() : 0)
                .asignado(domain.getAsignado() != null ? domain.getAsignado() : 0)
                .anulada(domain.getAnulada() != null ? domain.getAnulada() : 0)
                .decreto104316(domain.getDecreto104316() != null ? domain.getDecreto104316() : 0)
                .letraComprobante(domain.getLetraComprobante())
                .montoExento(domain.getMontoExento() != null ? domain.getMontoExento() : BigDecimal.ZERO)
                .reservaId(domain.getReservaId() != null ? domain.getReservaId() : 0L)
                .montoCuentaCorriente(domain.getMontoCuentaCorriente() != null ? domain.getMontoCuentaCorriente() : BigDecimal.ZERO)
                .cierreCajaId(domain.getCierreCajaId() != null ? domain.getCierreCajaId() : 0L)
                .cierreRestaurantId(domain.getCierreRestaurantId() != null ? domain.getCierreRestaurantId() : 0L)
                .nivel(domain.getNivel() != null ? domain.getNivel() : 0)
                .eliminar(domain.getEliminar() != null ? domain.getEliminar() : 0)
                .cuentaCorriente(domain.getCuentaCorriente() != null ? domain.getCuentaCorriente() : 0)
                .letras(domain.getLetras() != null ? domain.getLetras() : "")
                .cae(domain.getCae() != null ? domain.getCae() : "")
                .caeVencimiento(domain.getCaeVencimiento() != null ? domain.getCaeVencimiento() : "")
                .codigoBarras(domain.getCodigoBarras() != null ? domain.getCodigoBarras() : "")
                .participacion(domain.getParticipacion() != null ? domain.getParticipacion() : BigDecimal.ZERO)
                .monedaId(domain.getMonedaId())
                .cotizacion(domain.getCotizacion() != null ? domain.getCotizacion() : BigDecimal.ZERO)
                .observaciones(domain.getObservaciones())
                .trackUuid(domain.getTrackUuid())
                .clienteMovimientoIdSlave(domain.getClienteMovimientoIdSlave() != null ? domain.getClienteMovimientoIdSlave() : 0L)
                .build();
    }

    public ClienteMovimientoInvoiceDataResponse toResponse(ClienteMovimiento clienteMovimiento) {
        if (clienteMovimiento == null) {
            return null;
        }
        return ClienteMovimientoInvoiceDataResponse.builder()
                .letraComprobante(clienteMovimiento.getLetraComprobante())
                .puntoVenta(clienteMovimiento.getPuntoVenta())
                .numeroComprobante(clienteMovimiento.getNumeroComprobante())
                .observaciones(clienteMovimiento.getObservaciones())
                .letras(clienteMovimiento.getLetras())
                .reservaId(clienteMovimiento.getReservaId())
                .neto(clienteMovimiento.getNeto())
                .montoExento(clienteMovimiento.getMontoExento())
                .montoIva(clienteMovimiento.getMontoIva())
                .montoIvaRni(clienteMovimiento.getMontoIvaRni())
                .importe(clienteMovimiento.getImporte())
                .empresa(empresaMapper.toResponse(clienteMovimiento.getEmpresa()))
                .cliente(clienteMapper.toResponse(clienteMovimiento.getCliente()))
                .moneda(monedaMapper.toResponse(clienteMovimiento.getMoneda()))
                .comprobante(comprobanteMapper.toInvoiceDataResponse(clienteMovimiento.getComprobante()))
                .articulos(
                        articuloMovimientoService.findAllByClienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
                                .stream()
                                .map(articuloMovimientoMapper::toResponse)
                                .toList()
                )
                .build();
    }

}

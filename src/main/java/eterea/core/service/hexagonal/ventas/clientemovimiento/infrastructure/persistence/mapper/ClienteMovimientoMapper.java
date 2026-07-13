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
                .puntoVenta(domain.getPuntoVenta())
                .numeroComprobante(domain.getNumeroComprobante())
                .fechaComprobante(domain.getFechaComprobante())
                .clienteId(domain.getClienteId())
                .legajoId(domain.getLegajoId())
                .fechaVencimiento(domain.getFechaVencimiento())
                .negocioId(domain.getNegocioId())
                .empresaId(domain.getEmpresaId())
                .importe(domain.getImporte())
                .cancelado(domain.getCancelado())
                .neto(domain.getNeto())
                .netoCancelado(domain.getNetoCancelado())
                .montoIva(domain.getMontoIva())
                .montoIvaRni(domain.getMontoIvaRni())
                .reintegroTurista(domain.getReintegroTurista())
                .fechaContable(domain.getFechaContable())
                .ordenContable(domain.getOrdenContable())
                .recibo(domain.getRecibo())
                .asignado(domain.getAsignado())
                .anulada(domain.getAnulada())
                .decreto104316(domain.getDecreto104316())
                .letraComprobante(domain.getLetraComprobante())
                .montoExento(domain.getMontoExento())
                .reservaId(domain.getReservaId())
                .montoCuentaCorriente(domain.getMontoCuentaCorriente())
                .cierreCajaId(domain.getCierreCajaId())
                .cierreRestaurantId(domain.getCierreRestaurantId())
                .nivel(domain.getNivel())
                .eliminar(domain.getEliminar())
                .cuentaCorriente(domain.getCuentaCorriente())
                .letras(domain.getLetras())
                .cae(domain.getCae())
                .caeVencimiento(domain.getCaeVencimiento())
                .codigoBarras(domain.getCodigoBarras())
                .participacion(domain.getParticipacion())
                .monedaId(domain.getMonedaId())
                .cotizacion(domain.getCotizacion())
                .observaciones(domain.getObservaciones())
                .trackUuid(domain.getTrackUuid())
                .clienteMovimientoIdSlave(domain.getClienteMovimientoIdSlave())
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

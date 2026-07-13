package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper.ComprobanteDtoMapper;
import eterea.core.service.hexagonal.empresa.infrastructure.web.mapper.EmpresaDtoMapper;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ClienteMovimientoRequest;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ClienteMovimientoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteMovimientoDtoMapper {

    private final ComprobanteDtoMapper comprobanteDtoMapper;
    private final ClienteDtoMapper clienteDtoMapper;
    private final MonedaDtoMapper monedaDtoMapper;
    private final EmpresaDtoMapper empresaDtoMapper;

    public ClienteMovimiento toDomain(ClienteMovimientoRequest request) {
        if (request == null) {
            return null;
        }
        return ClienteMovimiento.builder()
                .comprobanteId(request.getComprobanteId())
                .puntoVenta(request.getPuntoVenta())
                .numeroComprobante(request.getNumeroComprobante())
                .fechaComprobante(request.getFechaComprobante())
                .clienteId(request.getClienteId())
                .legajoId(request.getLegajoId())
                .fechaVencimiento(request.getFechaVencimiento())
                .negocioId(request.getNegocioId())
                .empresaId(request.getEmpresaId())
                .importe(request.getImporte())
                .cancelado(request.getCancelado())
                .neto(request.getNeto())
                .netoCancelado(request.getNetoCancelado())
                .montoIva(request.getMontoIva())
                .montoIvaRni(request.getMontoIvaRni())
                .reintegroTurista(request.getReintegroTurista())
                .fechaContable(request.getFechaContable())
                .ordenContable(request.getOrdenContable())
                .recibo(request.getRecibo())
                .asignado(request.getAsignado())
                .anulada(request.getAnulada())
                .decreto104316(request.getDecreto104316())
                .letraComprobante(request.getLetraComprobante())
                .montoExento(request.getMontoExento())
                .reservaId(request.getReservaId())
                .montoCuentaCorriente(request.getMontoCuentaCorriente())
                .cierreCajaId(request.getCierreCajaId())
                .cierreRestaurantId(request.getCierreRestaurantId())
                .nivel(request.getNivel())
                .eliminar(request.getEliminar())
                .cuentaCorriente(request.getCuentaCorriente())
                .letras(request.getLetras())
                .cae(request.getCae())
                .caeVencimiento(request.getCaeVencimiento())
                .codigoBarras(request.getCodigoBarras())
                .participacion(request.getParticipacion())
                .monedaId(request.getMonedaId())
                .cotizacion(request.getCotizacion())
                .observaciones(request.getObservaciones())
                .trackUuid(request.getTrackUuid())
                .clienteMovimientoIdSlave(request.getClienteMovimientoIdSlave())
                .build();
    }

    public ClienteMovimientoResponse toResponse(ClienteMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return ClienteMovimientoResponse.builder()
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
                .comprobante(comprobanteDtoMapper.toResponse(domain.getComprobante()))
                .cliente(clienteDtoMapper.toResponse(domain.getCliente()))
                .moneda(monedaDtoMapper.toResponse(domain.getMoneda()))
                .empresa(empresaDtoMapper.toResponse(domain.getEmpresa()))
                .build();
    }
}

package eterea.core.service.hexagonal.reservas.voucher.infrastructure.web.mapper;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.infrastructure.web.dto.VoucherResponse;
import org.springframework.stereotype.Component;

@Component
public class VoucherDtoMapper {

    public VoucherResponse toResponse(Voucher domain) {
        if (domain == null) {
            return null;
        }
        return VoucherResponse.builder()
                .voucherId(domain.getVoucherId())
                .fechaToma(domain.getFechaToma())
                .fechaServicio(domain.getFechaServicio())
                .fechaVencimiento(domain.getFechaVencimiento())
                .horaVencimiento(domain.getHoraVencimiento())
                .nombrePax(domain.getNombrePax())
                .paxs(domain.getPaxs())
                .subeEn(domain.getSubeEn())
                .productos(domain.getProductos())
                .tieneVoucher(domain.getTieneVoucher())
                .clienteId(domain.getClienteId())
                .observaciones(domain.getObservaciones())
                .confirmado(domain.getConfirmado())
                .pagaCacheuta(domain.getPagaCacheuta())
                .hotelId(domain.getHotelId())
                .contacto(domain.getContacto())
                .paxsReales(domain.getPaxsReales())
                .proveedorId(domain.getProveedorId())
                .planilla(domain.getPlanilla())
                .reservaId(domain.getReservaId())
                .numeroVoucher(domain.getNumeroVoucher())
                .usuario(domain.getUsuario())
                .fechaRecepcion(domain.getFechaRecepcion())
                .fechaEmision(domain.getFechaEmision())
                .numero(domain.getNumero())
                .cantidadPax(domain.getCantidadPax())
                .nombre(domain.getNombre())
                .conTraslado(domain.getConTraslado())
                .paxsNoShow(domain.getPaxsNoShow())
                .reservaOrigenId(domain.getReservaOrigenId())
                .fechaAbierta(domain.getFechaAbierta())
                .ventaInternet(domain.getVentaInternet())
                .trackUuid(domain.getTrackUuid())
                .user(domain.getUser())
                .cliente(domain.getCliente())
                .hotel(domain.getHotel())
                .proveedor(domain.getProveedor())
                .reserva(domain.getReserva())
                .build();
    }

}

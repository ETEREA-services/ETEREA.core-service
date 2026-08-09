package eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.mapper.ProveedorMapper;
import eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.entity.VoucherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoucherMapper {

    private final ProveedorMapper proveedorMapper;

    public VoucherEntity toEntity(Voucher domain) {
        if (domain == null) {
            return null;
        }
        VoucherEntity.VoucherEntityBuilder builder = VoucherEntity.builder()
                .voucherId(domain.getVoucherId())
                .fechaToma(domain.getFechaToma())
                .fechaServicio(domain.getFechaServicio())
                .fechaVencimiento(domain.getFechaVencimiento())
                .nombrePax(domain.getNombrePax())
                .paxs(domain.getPaxs())
                .productos(domain.getProductos())
                .tieneVoucher(domain.getTieneVoucher())
                .clienteId(domain.getClienteId())
                .observaciones(domain.getObservaciones())
                .confirmado(domain.getConfirmado())
                .pagaCacheuta(domain.getPagaCacheuta())
                .hotelId(domain.getHotelId())
                .paxsReales(domain.getPaxsReales())
                .proveedorId(domain.getProveedorId())
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
                .trackUuid(domain.getTrackUuid())
                .user(domain.getUser())
                .cliente(domain.getCliente())
                .hotel(domain.getHotel())
                .proveedor(proveedorMapper.toEntity(domain.getProveedor()))
                .reserva(domain.getReserva());
        if (domain.getHoraVencimiento() != null) {
            builder.horaVencimiento(domain.getHoraVencimiento());
        }
        if (domain.getSubeEn() != null) {
            builder.subeEn(domain.getSubeEn());
        }
        if (domain.getContacto() != null) {
            builder.contacto(domain.getContacto());
        }
        if (domain.getPlanilla() != null) {
            builder.planilla(domain.getPlanilla());
        }
        if (domain.getFechaAbierta() != null) {
            builder.fechaAbierta(domain.getFechaAbierta());
        }
        if (domain.getVentaInternet() != null) {
            builder.ventaInternet(domain.getVentaInternet());
        }
        return builder.build();
    }

    public Voucher toDomain(VoucherEntity entity) {
        if (entity == null) {
            return null;
        }
        Voucher.VoucherBuilder builder = Voucher.builder()
                .voucherId(entity.getVoucherId())
                .fechaToma(entity.getFechaToma())
                .fechaServicio(entity.getFechaServicio())
                .fechaVencimiento(entity.getFechaVencimiento())
                .nombrePax(entity.getNombrePax())
                .paxs(entity.getPaxs())
                .productos(entity.getProductos())
                .tieneVoucher(entity.getTieneVoucher())
                .clienteId(entity.getClienteId())
                .observaciones(entity.getObservaciones())
                .confirmado(entity.getConfirmado())
                .pagaCacheuta(entity.getPagaCacheuta())
                .hotelId(entity.getHotelId())
                .paxsReales(entity.getPaxsReales())
                .proveedorId(entity.getProveedorId())
                .reservaId(entity.getReservaId())
                .numeroVoucher(entity.getNumeroVoucher())
                .usuario(entity.getUsuario())
                .fechaRecepcion(entity.getFechaRecepcion())
                .fechaEmision(entity.getFechaEmision())
                .numero(entity.getNumero())
                .cantidadPax(entity.getCantidadPax())
                .nombre(entity.getNombre())
                .conTraslado(entity.getConTraslado())
                .paxsNoShow(entity.getPaxsNoShow())
                .reservaOrigenId(entity.getReservaOrigenId())
                .trackUuid(entity.getTrackUuid())
                .user(entity.getUser())
                .cliente(entity.getCliente())
                .hotel(entity.getHotel())
                .proveedor(proveedorMapper.toDomain(entity.getProveedor()))
                .reserva(entity.getReserva());
        if (entity.getHoraVencimiento() != null) {
            builder.horaVencimiento(entity.getHoraVencimiento());
        }
        if (entity.getSubeEn() != null) {
            builder.subeEn(entity.getSubeEn());
        }
        if (entity.getContacto() != null) {
            builder.contacto(entity.getContacto());
        }
        if (entity.getPlanilla() != null) {
            builder.planilla(entity.getPlanilla());
        }
        if (entity.getFechaAbierta() != null) {
            builder.fechaAbierta(entity.getFechaAbierta());
        }
        if (entity.getVentaInternet() != null) {
            builder.ventaInternet(entity.getVentaInternet());
        }
        return builder.build();
    }

}

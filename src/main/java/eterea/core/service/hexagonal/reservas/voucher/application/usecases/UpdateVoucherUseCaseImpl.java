package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.application.exception.VoucherException;
import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.UpdateVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateVoucherUseCaseImpl implements UpdateVoucherUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public Voucher update(Voucher newVoucher, Long voucherId) {
        Voucher voucher = voucherRepository.findByVoucherId(voucherId)
                .orElseThrow(() -> new VoucherException(voucherId, "Programa por el Dia"));

        voucher.setFechaToma(newVoucher.getFechaToma());
        voucher.setFechaServicio(newVoucher.getFechaServicio());
        voucher.setFechaVencimiento(newVoucher.getFechaVencimiento());
        voucher.setHoraVencimiento(newVoucher.getHoraVencimiento());
        voucher.setNombrePax(newVoucher.getNombrePax());
        voucher.setPaxs(newVoucher.getPaxs());
        voucher.setSubeEn(newVoucher.getSubeEn());
        voucher.setProductos(newVoucher.getProductos());
        voucher.setTieneVoucher(newVoucher.getTieneVoucher());
        voucher.setClienteId(newVoucher.getClienteId());
        voucher.setObservaciones(newVoucher.getObservaciones());
        voucher.setConfirmado(newVoucher.getConfirmado());
        voucher.setPagaCacheuta(newVoucher.getPagaCacheuta());
        voucher.setHotelId(newVoucher.getHotelId());
        voucher.setContacto(newVoucher.getContacto());
        voucher.setPaxsReales(newVoucher.getPaxsReales());
        voucher.setProveedorId(newVoucher.getProveedorId());
        voucher.setPlanilla(newVoucher.getPlanilla());
        voucher.setReservaId(newVoucher.getReservaId());
        voucher.setNumeroVoucher(newVoucher.getNumeroVoucher());
        voucher.setUsuario(newVoucher.getUsuario());
        voucher.setFechaRecepcion(newVoucher.getFechaRecepcion());
        voucher.setFechaEmision(newVoucher.getFechaEmision());
        voucher.setNumero(newVoucher.getNumero());
        voucher.setCantidadPax(newVoucher.getCantidadPax());
        voucher.setNombre(newVoucher.getNombre());
        voucher.setConTraslado(newVoucher.getConTraslado());
        voucher.setPaxsNoShow(newVoucher.getPaxsNoShow());
        voucher.setReservaOrigenId(newVoucher.getReservaOrigenId());
        voucher.setFechaAbierta(newVoucher.getFechaAbierta());
        voucher.setVentaInternet(newVoucher.getVentaInternet());
        voucher.setTrackUuid(newVoucher.getTrackUuid());

        return voucherRepository.save(voucher);
    }

}

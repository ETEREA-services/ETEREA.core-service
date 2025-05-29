/**
 *
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.exception.VoucherException;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.repository.VoucherRepository;
import eterea.core.service.tool.ToolService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 */
@Service
@Slf4j
public class VoucherService {

    private final VoucherRepository repository;

    public VoucherService(VoucherRepository repository) {
        this.repository = repository;
    }

    public List<Voucher> findAllByUserToday(String login) {
        return repository.findAllByFechaVencimientoAndUsuario(ToolService.dateAbsoluteArgentina(), login);
    }

    public List<Voucher> findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
            Boolean porNombrePax) {
        Sort sort = null;
        if (!porNombrePax) {
            sort = Sort.by("cliente.razonSocial").ascending();
        }
        if (sort == null) {
            sort = Sort.by("nombrePax").ascending();
        } else {
            sort = sort.and(Sort.by("nombrePax").ascending());
        }
        List<Voucher> vouchers = repository.findAllByFechaServicio(fechaServicio, sort);
        if (soloConfirmados) {
            vouchers = vouchers.stream().filter(voucher -> voucher.getConfirmado() == 1).collect(Collectors.toList());
        }
        return vouchers;
    }

    public Voucher findByReservaId(Long reservaId) {
        return repository.findByReservaId(reservaId)
                .orElseThrow(() -> new VoucherException(reservaId, "Reserva"));
    }

    public Voucher findByVoucherId(Long voucherId) {
        return Objects.requireNonNull(repository.findByVoucherId(voucherId))
                .orElseThrow(() -> new VoucherException(voucherId, "Programa por el Día"));
    }

    public Voucher findByNumeroVoucher(String numeroVoucher) {
        return repository.findTopByNumeroVoucherContains(numeroVoucher)
                .orElseThrow(() -> new VoucherException(numeroVoucher));
    }

    public Voucher findByNumeroVoucherAlreadyRegistered(String numeroVoucher) {
        log.debug("Processing findByNumeroVoucherAlreadyRegistered");
        return Objects
                .requireNonNull(repository.findTopByNumeroVoucherContainsAndFechaTomaAfter(numeroVoucher,
                        OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
                .orElseThrow(() -> new VoucherException(numeroVoucher));
    }

    public Voucher save(Voucher voucher) {
        if (voucher.getVoucherId() == null) {
            voucher = add(voucher);
        } else {
            voucher = update(voucher, voucher.getVoucherId());
        }
        return voucher;
    }

    public Voucher add(Voucher voucher) {
        return repository.save(voucher);
    }

    public Voucher update(Voucher newVoucher, Long voucherId) {
        return repository.findByVoucherId(voucherId).map(voucher -> {
            voucher = new Voucher.Builder()
                    .voucherId(voucherId)
                    .fechaToma(newVoucher.getFechaToma())
                    .fechaServicio(newVoucher.getFechaServicio())
                    .fechaVencimiento(newVoucher.getFechaVencimiento())
                    .horaVencimiento(newVoucher.getHoraVencimiento())
                    .nombrePax(newVoucher.getNombrePax())
                    .paxs(newVoucher.getPaxs())
                    .subeEn(newVoucher.getSubeEn())
                    .productos(newVoucher.getProductos())
                    .tieneVoucher(newVoucher.getTieneVoucher())
                    .clienteId(newVoucher.getClienteId())
                    .observaciones(newVoucher.getObservaciones())
                    .confirmado(newVoucher.getConfirmado())
                    .pagaCacheuta(newVoucher.getPagaCacheuta())
                    .hotelId(newVoucher.getHotelId())
                    .contacto(newVoucher.getContacto())
                    .paxsReales(newVoucher.getPaxsReales())
                    .proveedorId(newVoucher.getProveedorId())
                    .planilla(newVoucher.getPlanilla())
                    .reservaId(newVoucher.getReservaId())
                    .numeroVoucher(newVoucher.getNumeroVoucher())
                    .usuario(newVoucher.getUsuario())
                    .fechaRecepcion(newVoucher.getFechaRecepcion())
                    .fechaEmision(newVoucher.getFechaEmision())
                    .numero(newVoucher.getNumero())
                    .cantidadPax(newVoucher.getCantidadPax())
                    .nombre(newVoucher.getNombre())
                    .conTraslado(newVoucher.getConTraslado())
                    .paxsNoShow(newVoucher.getPaxsNoShow())
                    .reservaOrigenId(newVoucher.getReservaOrigenId())
                    .fechaAbierta(newVoucher.getFechaAbierta())
                    .ventaInternet(newVoucher.getVentaInternet())
                    .build();
            return repository.save(voucher);
        }).orElseThrow(() -> new VoucherException(voucherId, "Programa por el Dia"));
    }

    /*
     * @author sebastian
     */

    public List<Voucher> findAllByNumeroVoucherIn(List<String> numerosVoucher) {
        return repository.findAllByNumeroVoucherIn(numerosVoucher);
    }

}

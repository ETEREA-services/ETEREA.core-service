/**
 *
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.exception.VoucherException;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.repository.VoucherRepository;
import eterea.core.service.model.Track;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.core.service.tool.ToolService;

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
        return repository.findTopByNumeroVoucherContains(numeroVoucher).orElseThrow(() -> new VoucherException(numeroVoucher));
    }

    public Voucher findByNumeroVoucherAlreadyRegistered(String numeroVoucher) {
        log.debug("Processing findByNumeroVoucherAlreadyRegistered");
        return Objects.requireNonNull(repository.findTopByNumeroVoucherContainsAndFechaTomaAfter(numeroVoucher, OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))).orElseThrow(() -> new VoucherException(numeroVoucher));
    }

    public Voucher save(Voucher voucher, Track track) {
        if (track != null) {
            voucher.setTrackUuid(track.getUuid());
        }
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
        Voucher voucher = repository.findByVoucherId(voucherId)
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

        return repository.save(voucher);
    }

}

/**
 * 
 */
package eterea.api.rest.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.VoucherNotFoundException;
import eterea.api.rest.model.Voucher;
import eterea.api.rest.repository.IVoucherRepository;
import eterea.api.rest.tool.ToolService;

/**
 * @author daniel
 *
 */
@Service
public class VoucherService {

	@Autowired
	private IVoucherRepository repository;

	public List<Voucher> findAllByUserToday(String login) {
		return repository.findAllByFechaVencimientoAndUsuario(ToolService.dateAbsoluteArgentina(), login);
	}

	public List<Voucher> findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
			Boolean porNombrePax) {
		Sort sort = null;
		if (porNombrePax == false) {
			sort = Sort.by("cliente.razonSocial").ascending();
		}
		if (sort == null) {
			sort = Sort.by("nombrePax").ascending();
		} else {
			sort = sort.and(Sort.by("nombrePax").ascending());
		}
		List<Voucher> vouchers = repository.findAllByFechaServicio(fechaServicio, sort);
		if (soloConfirmados == true) {
			vouchers = vouchers.stream().filter(voucher -> voucher.getConfirmado() == 1).collect(Collectors.toList());
		}
		return vouchers;
	}

	public Voucher findByReservaId(Long reservaId) {
		return repository.findByReservaId(reservaId)
				.orElseThrow(() -> new VoucherNotFoundException(reservaId, "Reserva"));
	}

	public Voucher findByVoucherId(Long voucherId) {
		return repository.findByVoucherId(voucherId)
				.orElseThrow(() -> new VoucherNotFoundException(voucherId, "Programa por el Día"));
	}

}

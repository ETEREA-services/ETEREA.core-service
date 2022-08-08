/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Voucher findByReservaId(Long reservaId) {
		return repository.findByReservaId(reservaId).orElseThrow(() -> new VoucherNotFoundException(reservaId));
	}

}

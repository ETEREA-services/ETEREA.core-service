/**
 * 
 */
package eterea.core.api.rest.service.facade;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eterea.core.api.rest.exception.ProgramaDiaException;
import eterea.core.api.rest.exception.VoucherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ClienteMovimiento;
import eterea.core.api.rest.model.ReservaOrigen;
import eterea.core.api.rest.model.Voucher;
import eterea.core.api.rest.model.dto.ProgramaDiaDTO;
import eterea.core.api.rest.service.ClienteMovimientoService;
import eterea.core.api.rest.service.ReservaOrigenService;
import eterea.core.api.rest.service.VoucherService;

/**
 * @author daniel
 *
 */
@Service
public class ProgramaDiaService {

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private ReservaOrigenService reservaOrigenService;

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	public ProgramaDiaDTO findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
			Boolean porNombrePax) {
		List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax);
		List<Long> reservaIds = vouchers.stream().filter(voucher -> voucher.getReservaId() > 0)
				.map(voucher -> voucher.getReservaId()).collect(Collectors.toList());
		List<ClienteMovimiento> clienteMovimientos = clienteMovimientoService.findAllByReservaIds(reservaIds);
		return new ProgramaDiaDTO(vouchers, reservaOrigenService.findAll(), clienteMovimientos);
	}

	public ProgramaDiaDTO findByVoucherId(Long voucherId) {
		Voucher voucher = null;
		try {
			voucher = voucherService.findByVoucherId(voucherId);
		} catch (VoucherException e) {
			throw new ProgramaDiaException(voucherId);
		}
		List<Voucher> vouchers = new ArrayList<>();
		vouchers.add(voucher);
		ReservaOrigen reservaOrigen = reservaOrigenService.findByReservaOrigenId(voucher.getReservaOrigenId());
		List<ReservaOrigen> reservaOrigens = new ArrayList<>();
		reservaOrigens.add(reservaOrigen);
		List<ClienteMovimiento> clienteMovimientos = clienteMovimientoService
				.findAllByReservaId(voucher.getReservaId());
		return new ProgramaDiaDTO(vouchers, reservaOrigens, clienteMovimientos);
	}

}

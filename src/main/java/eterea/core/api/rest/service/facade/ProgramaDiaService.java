/**
 *
 */
package eterea.core.api.rest.service.facade;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eterea.core.api.rest.exception.ClienteException;
import eterea.core.api.rest.exception.ProgramaDiaException;
import eterea.core.api.rest.exception.VoucherException;
import eterea.core.api.rest.kotlin.extern.OrderNote;
import eterea.core.api.rest.kotlin.model.*;
import eterea.core.api.rest.kotlin.model.dto.ProgramaDiaDTO;
import eterea.core.api.rest.service.*;
import eterea.core.api.rest.service.extern.OrderNoteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 */
@Service
@Slf4j
public class ProgramaDiaService {

    private final VoucherService voucherService;

    private final ReservaOrigenService reservaOrigenService;

    private final ClienteMovimientoService clienteMovimientoService;

    private final OrderNoteService orderNoteService;

    private final ClienteService clienteService;

    private final EmpresaService empresaService;

    private final NegocioService negocioService;

    @Autowired
    public ProgramaDiaService(VoucherService voucherService, ReservaOrigenService reservaOrigenService, ClienteMovimientoService clienteMovimientoService, OrderNoteService orderNoteService, ClienteService clienteService, EmpresaService empresaService, NegocioService negocioService) {
        this.voucherService = voucherService;
        this.reservaOrigenService = reservaOrigenService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.orderNoteService = orderNoteService;
        this.clienteService = clienteService;
        this.empresaService = empresaService;
        this.negocioService = negocioService;
    }

    public ProgramaDiaDTO findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
                                                 Boolean porNombrePax) {
        List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax);
        List<Long> reservaIds = vouchers.stream().map(Voucher::getReservaId)
                .filter(reservaId -> reservaId > 0).collect(Collectors.toList());
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

    @Transactional
    public ProgramaDiaDTO importFromWeb(Long orderNumberId) {
        Empresa empresa = empresaService.findTop();
        Negocio negocio = negocioService.findByNegocioId(empresa.getNegocioId());
        OrderNote orderNote = orderNoteService.findByOrderNumberId(orderNumberId);
        if (!orderNote.getOrderStatus().equals("Completado")) {
            return null;
        }
        // Verifica cliente
        Cliente cliente = null;
        try {
            cliente = clienteService.findByNumeroDocumento(orderNote.getBillingDniPasaporte());
        } catch (ClienteException e) {
            Long clienteId = 1 + clienteService.findLast().getClienteId();
            cliente = new Cliente(clienteId, orderNote.getBillingFullName(), negocio.getNegocioId(), "", "", "", null, 0, 0, orderNote.getBillingAddress(), orderNote.getBillingPhone(), "", orderNote.getBillingEmail(), orderNote.getBillingPhone(), 3, 0, 0, "", orderNote.getBillingDniPasaporte(), BigDecimal.ZERO, orderNote.getBillingCountry(), 0, "", (byte) 0, (byte) 0, (byte) 0);
            cliente = clienteService.add(cliente);
        }
//        Voucher voucher = new Voucher(null, orderNote.getCompletedDate(), orderNote.getProducts().get(0).getBookingStart(), orderNote.getProducts().get(0).getBookingStart(), Time.valueOf("00:00:00"), orderNote.getBillingFullName(), );
        return null;
    }

}

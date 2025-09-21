package eterea.core.service.service.facade;

import eterea.core.service.kotlin.exception.VoucherException;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.extern.Product;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import eterea.core.service.model.Track;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.service.facade.reserva.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class VouchersService {

    private final EmpresaService empresaService;
    private final NegocioService negocioService;
    private final OrderNoteService orderNoteService;
    private final VoucherService voucherService;
    private final ProductsService productsService;

    public ProgramaDiaDto importOneFromWeb(Long orderNumberId, Track track) {
        log.debug("Processing importOneFromWeb");
        OrderNote orderNote = getOrderNoteById(orderNumberId);
        if (orderNote == null || !isOrderCompleted(orderNote)) {
            return createErrorResponse("Error: Order Note pendiente de PAGO");
        }
        log.debug("OrderNote -> {}", orderNote.jsonify());

        if (isVoucherAlreadyRegistered(orderNumberId)) {
            return createErrorResponse("Error: Programa por el Día YA registrado");
        }

        if (Objects.requireNonNull(orderNote.getProducts()).isEmpty()) {
            return createErrorResponse("Error: reserva sin productos");
        }

        if (orderNote.getProducts().size() > 1) {
            return createErrorResponse("Error: más de un producto en la reserva");
        }

        Product product = orderNote.getProducts().getFirst();
        assert product != null;
        return processProduct(orderNote, product, negocioService.findByNegocioId(empresaService.findTop().getNegocioId()), track);
    }

    private OrderNote getOrderNoteById(Long orderNumberId) {
        log.debug("Processing getOrderNoteById");
        return orderNoteService.findByOrderNumberId(orderNumberId);
    }

    private boolean isOrderCompleted(OrderNote orderNote) {
        log.debug("Processing isOrderCompleted");
        var payment = orderNote.getPayment();
        if (payment == null) {
            return false;
        }
        if (payment.getEstado().equals("EXPIRADA")) {
            return false;
        }
        return Arrays.asList("Completado", "Completed").contains(orderNote.getOrderStatus());
    }

    private boolean isVoucherAlreadyRegistered(Long orderNumberId) {
        log.debug("Processing isVoucherAlreadyRegistered");
        try {
            voucherService.findByNumeroVoucherAlreadyRegistered(String.valueOf(orderNumberId));
            return true;
        } catch (VoucherException e) {
            log.debug("Voucher not found, proceeding.");
            return false;
        }
    }

    private ProgramaDiaDto createErrorResponse(String message) {
        log.debug("Processing createErrorResponse");
        return new ProgramaDiaDto.Builder().errorMessage(message).build();
    }

    private ProgramaDiaDto processProduct(OrderNote orderNote, Product product, Negocio negocio, Track track) {
        log.debug("Processing VouchersService.processProduct");
        switch (product.getSku()) {
            case "parque_termal":
            case "tarde_termaspa":
                return productsService.processOneProduct(orderNote, 130, 475, product, negocio, track);
            case "termaspa_fullday":
                if (product.getServiciosAdicionales().isEmpty()) {
                    return productsService.processOneProduct(orderNote, 130, 475, product, negocio, track);
                }
                break;
            case "parque_termal_traslado":
                if (product.getServiciosAdicionales().isEmpty()) {
                    return productsService.processOneProduct(orderNote, 31, 475, product, negocio, track);
                }
                break;
            case null:
                break;
            default:
                break;
        }
        return createErrorResponse("Error: no corresponde a un producto facturable");
    }

}

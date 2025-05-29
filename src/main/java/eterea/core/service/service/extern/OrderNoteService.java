package eterea.core.service.service.extern;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import eterea.core.service.client.web.OrderNoteClient;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.service.VoucherService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderNoteService {

    private final OrderNoteClient orderNoteClient;
    private final VoucherService voucherService;

    public OrderNoteService(OrderNoteClient orderNoteClient, VoucherService voucherService) {
        this.orderNoteClient = orderNoteClient;
        this.voucherService = voucherService;
    }

    public OrderNote findByOrderNumberId(@PathVariable Long orderNumberId) {
        log.debug("Processing findByOrderNumberId");
        return orderNoteClient.findByOrderNumberId(orderNumberId);
    }

    public List<OrderNote> findAllCompletedByLastTwoDays() {
        return orderNoteClient.findAllCompletedByLastTwoDays();
    }

}
package eterea.core.service.service.extern;

import eterea.core.service.client.OrderNoteClient;
import eterea.core.service.kotlin.extern.OrderNote;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderNoteService {

    private final OrderNoteClient orderNoteClient;

    public OrderNoteService(OrderNoteClient orderNoteClient) {
        this.orderNoteClient = orderNoteClient;
    }

    public OrderNote findByOrderNumberId(@PathVariable Long orderNumberId) {
        return orderNoteClient.findByOrderNumberId(orderNumberId);
    }

    public List<OrderNote> findAllCompletedByLastTwoDays() {
        return orderNoteClient.findAllCompletedByLastTwoDays();
    }

}

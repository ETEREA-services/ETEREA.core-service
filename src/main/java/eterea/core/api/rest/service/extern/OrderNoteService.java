package eterea.core.api.rest.service.extern;

import eterea.core.api.rest.client.OrderNoteClient;
import eterea.core.api.rest.kotlin.extern.OrderNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderNoteService {

    private final OrderNoteClient orderNoteClient;

    @Autowired
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

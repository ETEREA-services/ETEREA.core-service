package eterea.core.service.controller.extern;

import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.service.extern.OrderNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/orderNote", "/orderNote"})
public class OrderNoteController {

    private final OrderNoteService service;

    public OrderNoteController(OrderNoteService service) {
        this.service = service;
    }

    @GetMapping("/{orderNumberId}")
    public ResponseEntity<OrderNote> getOrderNote(@PathVariable Long orderNumberId) {
        return new ResponseEntity<>(service.findByOrderNumberId(orderNumberId), HttpStatus.OK);
    }

}

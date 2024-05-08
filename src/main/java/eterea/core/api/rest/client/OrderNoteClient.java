package eterea.core.api.rest.client;

import eterea.core.api.rest.kotlin.extern.OrderNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "import-service/orderNote")
public interface OrderNoteClient {

    @GetMapping("/{orderNumberId}")
    OrderNote findByOrderNumberId(@PathVariable Long orderNumberId);

    @GetMapping("/lastTwoDays")
    List<OrderNote> findAllCompletedByLastTwoDays();

}

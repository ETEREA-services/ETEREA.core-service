package eterea.core.service.client.reservas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import eterea.core.service.model.dto.pluspagos.ApiResponseDto;
import eterea.core.service.model.dto.pluspagos.PluspagosTransactionDto;

@FeignClient("http://10.144.20.14:8080/api/v1/pluspagos")
public interface PluspagosClient {
   @GetMapping("/transactions/order-note/{orderNoteId}")
   ResponseEntity<ApiResponseDto<PluspagosTransactionDto>> getTransactionByOrderNote(@PathVariable String orderNoteId);

}
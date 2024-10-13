package eterea.core.service.client.report;

import jakarta.mail.MessagingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;

@FeignClient("report-service/api/report/makeFactura")
public interface MakeFacturaReportClient {

    @GetMapping("/pdf/{clienteMovimientoId}")
    Resource makePdf(@PathVariable Long clienteMovimientoId) throws FileNotFoundException;

    @GetMapping("/send/{clienteMovimientoId}/{email}")
    String send(@PathVariable Long clienteMovimientoId, @PathVariable String email) throws MessagingException;

}

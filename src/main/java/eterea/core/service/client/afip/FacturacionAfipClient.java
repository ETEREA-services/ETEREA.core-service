package eterea.core.service.client.afip;

import eterea.core.service.model.dto.FacturacionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("pyafipws-service/api/afipws")
public interface FacturacionAfipClient {

    @GetMapping("/test")
    String test();

    @PostMapping("/facturador")
    FacturacionDto facturador(FacturacionDto facturacionDto);

}

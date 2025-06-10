package eterea.core.service.client.afip;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eterea.core.service.kotlin.model.dto.FacturacionDto;

// @FeignClient("http://10.147.17.149:5000/api/afipws") // PROD
@FeignClient("pyafipws-service/api/afipws") // TESTING
public interface FacturacionAfipClient {

    @GetMapping("/test")
    String test();

    @PostMapping("/facturador")
    FacturacionDto facturador(FacturacionDto facturacionDto);

}

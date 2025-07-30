package eterea.core.service.client.afip;

import eterea.core.service.model.client.pyafipws.FacturaResponseDto;
import eterea.core.service.model.dto.FacturacionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("pyafipws-service/api/afipws")
public interface FacturacionAfipClient {

    @GetMapping("/test")
    String test();

    @PostMapping("/facturador")
    FacturacionDto facturador(FacturacionDto facturacionDto);

    @GetMapping("/consulta_comprobante")
    FacturaResponseDto consultaComprobante(@RequestParam("tipo_cbte") Integer tipoCbte,
                                           @RequestParam("punto_vta") Integer puntoVta,
                                           @RequestParam("cbte_nro") Long cbteNro);

}

package eterea.core.service.service.extern;

import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.model.dto.FacturacionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;

    public FacturacionElectronicaService(FacturacionAfipClient facturacionAfipClient
    ) {
        this.facturacionAfipClient = facturacionAfipClient;
    }

    public FacturacionDto makeFactura(FacturacionDto facturacionDto) {
        log.debug("Processing FactuacionElectronicaService.makeFactura");
        log.debug("FacturacionDto (pre) -> {}", facturacionDto.jsonify());
        facturacionDto = facturacionAfipClient.facturador(facturacionDto);
        log.debug("FacturacionDto (post) -> {}", facturacionDto.jsonify());
        return facturacionDto;
    }

}

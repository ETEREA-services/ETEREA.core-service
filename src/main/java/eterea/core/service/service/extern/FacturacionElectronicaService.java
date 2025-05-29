package eterea.core.service.service.extern;

import org.springframework.stereotype.Service;

import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;

    public FacturacionElectronicaService(FacturacionAfipClient facturacionAfipClient) {
        this.facturacionAfipClient = facturacionAfipClient;
    }

    public FacturacionDto makeFactura(FacturacionDto facturacionDTO) {
        return facturacionAfipClient.facturador(facturacionDTO);
    }

}

package eterea.core.service.hexagonal.facturacion.arca.nacional.application.service;

import eterea.core.service.client.arca.FacturacionAfipClient;
import eterea.core.service.hexagonal.facturacion.arca.nacional.infrastructure.web.dto.FacturacionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;

    public FacturacionDto makeFactura(FacturacionDto facturacionDto) {
        log.debug("Processing FactuacionElectronicaService.makeFactura");
        log.debug("FacturacionDto (pre) -> {}", facturacionDto.jsonify());
        facturacionDto = facturacionAfipClient.facturador(facturacionDto);
        log.debug("FacturacionDto (post) -> {}", facturacionDto.jsonify());
        return facturacionDto;
    }

}

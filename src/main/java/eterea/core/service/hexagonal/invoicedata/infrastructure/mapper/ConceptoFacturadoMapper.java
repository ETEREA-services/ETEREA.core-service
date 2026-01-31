package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ConceptoFacturadoResponse;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import org.springframework.stereotype.Component;

@Component
public class ConceptoFacturadoMapper {

    public ConceptoFacturadoResponse toResponse(ConceptoFacturado conceptoFacturado) {
        if (conceptoFacturado == null) {
            return null;
        }
        return ConceptoFacturadoResponse.builder()
                .concepto(conceptoFacturado.getConcepto())
                .build();
    }

}

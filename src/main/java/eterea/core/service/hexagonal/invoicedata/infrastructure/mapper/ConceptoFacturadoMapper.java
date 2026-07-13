package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ConceptoFacturadoInvoiceDataResponse;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import org.springframework.stereotype.Component;

@Component
public class ConceptoFacturadoMapper {

    public ConceptoFacturadoInvoiceDataResponse toResponse(ConceptoFacturado conceptoFacturado) {
        if (conceptoFacturado == null) {
            return null;
        }
        return ConceptoFacturadoInvoiceDataResponse.builder()
                .concepto(conceptoFacturado.getConcepto())
                .build();
    }

}

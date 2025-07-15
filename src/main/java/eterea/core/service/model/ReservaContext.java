package eterea.core.service.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReservaContext extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservaContextId;
    private Long reservaId;
    private Long voucherId;
    private Long clienteMovimientoId;
    private Long orderNumberId;
    @Builder.Default
    private Byte facturadoFuera = 0;
    @Builder.Default
    private Byte facturaPendiente = 0;
    @Builder.Default
    private Integer facturaTries = 0;
    @Builder.Default
    private Byte envioPendiente = 0;
    @Builder.Default
    private Integer envioTries = 0;
    @Builder.Default
    private BigDecimal diferenciaWeb = BigDecimal.ZERO;
    @Builder.Default
    private Byte facturaArca = 0;
    private String payloadArca;

    public String jsonify() {
        try {
            return JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "jsonify error: " + e.getMessage();
        }
    }

}

package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.model.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "cierre_caja_anticipo_haberes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaAnticipoHaberesEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cierreCajaAnticipoHaberesId;

    private Long cierreCajaId;
    private Integer legajoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fecha;

    private BigDecimal importe;
    private Long userId;

}

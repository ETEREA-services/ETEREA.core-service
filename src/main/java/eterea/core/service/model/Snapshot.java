package eterea.core.service.model;

import eterea.core.service.kotlin.model.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Snapshot extends Auditable {

    @Id
    private String uuid;

    private String descripcion;
    private String payload;

}

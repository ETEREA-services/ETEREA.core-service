package eterea.core.service.model;

import eterea.core.service.kotlin.model.Auditable;
import jakarta.persistence.*;
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

    private String trackUuid;
    private String previousUuid;
    private String descripcion;

    @Lob
    private String payload;

}

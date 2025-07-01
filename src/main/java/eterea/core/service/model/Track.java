package eterea.core.service.model;

import eterea.core.service.kotlin.model.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Track extends Auditable {

    @Id
    private String uuid;

    private String descripcion;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'IN_PROGRESS'")
    private Status status = Status.IN_PROGRESS;

    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }

}

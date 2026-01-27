package eterea.core.service.hexagonal.legajo.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "legajo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegajoEntity extends Auditable {

    @Id
    @Column(name = "leg_id")
    private Integer legajoId;

    @Builder.Default
    @Column(name = "leg_apellido")
    private String apellido = "";

    @Builder.Default
    @Column(name = "leg_nombre")
    private String nombre = "";

    @Column(name = "leg_doc_id")
    private Integer documentoId;

    @Builder.Default
    @Column(name = "leg_telefono")
    private String telefono = "";

    @Builder.Default
    @Column(name = "leg_celular")
    private String celular = "";

    @Builder.Default
    @Column(name = "leg_email")
    private String email = "";

    @Column(name = "leg_nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaNacimiento;

    @Column(name = "leg_nrodoc")
    private BigDecimal numeroDocumento;

    @Builder.Default
    @Column(name = "leg_cuil")
    private String cuil = "";

    @Builder.Default
    @Column(name = "leg_calle")
    private String calle = "";

    @Builder.Default
    @Column(name = "leg_numero")
    private String numero = "";

    @Builder.Default
    @Column(name = "leg_piso")
    private String piso = "";

    @Builder.Default
    @Column(name = "leg_dpto")
    private String departamento = "";

    @Builder.Default
    @Column(name = "leg_localidad")
    private String localidad = "";

    @Builder.Default
    @Column(name = "leg_provincia")
    private String provincia = "";

    @Column(name = "leg_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaIngreso;

    @Column(name = "leg_neg_id")
    private Integer negocioId;

}

package eterea.core.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Legajo extends Auditable {

    @Id
    @Column(name = "leg_id")
    private Integer legajoId;

    @Column(name = "leg_apellido")
    private String apellido = "";

    @Column(name = "leg_nombre")
    private String nombre = "";

    @Column(name = "leg_doc_id")
    private Integer documentoId;

    @Column(name = "leg_telefono")
    private String telefono = "";

    @Column(name = "leg_celular")
    private String celular = "";

    @Column(name = "leg_email")
    private String email = "";

    @Column(name = "leg_nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaNacimiento;

    @Column(name = "leg_nrodoc")
    private BigDecimal numeroDocumento;

    @Column(name = "leg_cuil")
    private String cuil = "";

    @Column(name = "leg_calle")
    private String calle = "";

    @Column(name = "leg_numero")
    private String numero = "";

    @Column(name = "leg_piso")
    private String piso = "";

    @Column(name = "leg_dpto")
    private String departamento = "";

    @Column(name = "leg_localidad")
    private String localidad = "";

    @Column(name = "leg_provincia")
    private String provincia = "";

    @Column(name = "leg_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaIngreso;

    @Column(name = "leg_neg_id")
    private Integer negocioId;

}

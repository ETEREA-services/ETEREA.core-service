package eterea.core.service.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "vw_legajo_search")
@Immutable
public class LegajoSearch {

    @Id
    private Integer legajoId;
    private String apellido = "";
    private String nombre = "";
    private Integer documentoId;
    private String telefono = "";
    private String celular = "";
    private String email = "";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaNacimiento;
    private BigDecimal numeroDocumento;
    private String cuil = "";
    private String calle = "";
    private String numero = "";
    private String piso = "";
    private String departamento = "";
    private String localidad = "";
    private String provincia = "";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaIngreso;
    private Integer negocioId;
    private String search;

}

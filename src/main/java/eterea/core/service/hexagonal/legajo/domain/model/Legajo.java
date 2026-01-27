package eterea.core.service.hexagonal.legajo.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Legajo {

    private Integer legajoId;
    private String apellido;
    private String nombre;
    private Integer documentoId;
    private String telefono;
    private String celular;
    private String email;
    private OffsetDateTime fechaNacimiento;
    private BigDecimal numeroDocumento;
    private String cuil;
    private String calle;
    private String numero;
    private String piso;
    private String departamento;
    private String localidad;
    private String provincia;
    private OffsetDateTime fechaIngreso;
    private Integer negocioId;

}

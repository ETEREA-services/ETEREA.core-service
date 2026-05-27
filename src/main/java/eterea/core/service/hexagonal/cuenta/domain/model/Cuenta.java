package eterea.core.service.hexagonal.cuenta.domain.model;

import eterea.core.service.tool.Jsonifier;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    private Long numeroCuenta;
    private String nombre;
    private Integer negocioId;
    private Byte integra;
    private Byte transfiere;
    private Byte movimientoStock;
    private BigDecimal cuentaMaestro;
    private Integer grado;
    private Long grado1;
    private Long grado2;
    private Long grado3;
    private Long grado4;
    private Byte ventas;
    private Byte compras;
    private Byte gastos;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }
}

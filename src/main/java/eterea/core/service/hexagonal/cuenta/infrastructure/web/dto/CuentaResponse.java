package eterea.core.service.hexagonal.cuenta.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponse {

    private Long numeroCuenta;
    private String nombre;
    private Integer negocioId;
    private Byte integra;
    private Byte transfiere;
    private Byte movimientoStock;
    @Builder.Default
    private BigDecimal cuentaMaestro = BigDecimal.ZERO;
    private Integer grado;
    private Long grado1;
    private Long grado2;
    private Long grado3;
    private Long grado4;
    private Byte ventas;
    private Byte compras;
    private Byte gastos;

}

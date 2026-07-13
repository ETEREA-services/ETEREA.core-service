package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorResponse {

    private Integer valorId;
    private String concepto;
    private Integer negocioId;
    private Byte compras;
    private Byte ventas;
    private Byte numerable;
    private Byte duplicados;
    private Byte fechaEmision;
    private Byte fechaVencimiento;
    private Long numeroCuenta;
    private Byte titular;
    private Byte banco;
    private Byte chequeTercero;
    private Byte cuentaCorriente;
    private Byte retencionIngresosBrutos;
    private Byte retencionGanancias;
    private Byte retencionIva;
    private Byte autoNumerable;
    private Integer monedaId;
    private Byte invisible;
    private Byte restaurant;
    private Byte tarjeta;

}

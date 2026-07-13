package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.tool.Jsonifyable;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorMovimiento implements Jsonifyable {

    private Long valorMovimientoId;
    private Integer negocioId;
    private Integer valorId;
    private Long proveedorId;
    private Long clienteId;
    private OffsetDateTime fechaEmision;
    private OffsetDateTime fechaVencimiento;
    private Integer comprobanteId;
    private Long numeroComprobante;
    private BigDecimal importe;
    private Long numeroCuenta;
    private OffsetDateTime fechaContable;
    private Integer ordenContable;
    private Long proveedorMovimientoId;
    private Long clienteMovimientoId;
    private String titular;
    private String banco;
    private String receptor;
    private Integer estadoId;
    private OffsetDateTime fechaEntrega;
    private Long tanda;
    private Long tandaIndex;
    private Long cierreCajaId;
    private Integer nivel;
    private String observaciones;
    private String trackUuid;

    private Valor valor;
    private Cuenta cuenta;

}

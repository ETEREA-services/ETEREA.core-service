package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.dto;

import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.dto.CuentaResponse;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ValorResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorMovimientoResponse {

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

    private ValorResponse valor;
    private CuentaResponse cuenta;

}

package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import eterea.core.service.tool.Jsonifyable;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimiento implements Jsonifyable {

    private Long cuentaMovimientoId;
    private OffsetDateTime fecha;
    private Integer orden;
    private Integer item;
    private Byte debita;
    private Integer negocioId;
    private Long numeroCuenta;
    private Integer comprobanteId;
    private String concepto;
    private BigDecimal importe;
    private Long subrubroId;
    private Long proveedorId;
    private Long clienteId;
    private Integer legajoId;
    private Long cierreCajaId;
    private Integer nivel;
    private Long firma;
    private Integer tipoAsientoId;
    private Long articuloMovimientoId;
    private Integer ejercicioId;
    private Byte inflacion;
    private String trackUuid;

    private Cuenta cuenta;
    private Comprobante comprobante;
    private Negocio negocio;

}

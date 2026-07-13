package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.dto;

import eterea.core.service.hexagonal.comprobante.infrastructure.web.dto.ComprobanteResponse;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.dto.CuentaResponse;
import eterea.core.service.hexagonal.negocio.infrastructure.web.dto.NegocioResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoResponse {

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

    private CuentaResponse cuenta;
    private ComprobanteResponse comprobante;
    private NegocioResponse negocio;

}

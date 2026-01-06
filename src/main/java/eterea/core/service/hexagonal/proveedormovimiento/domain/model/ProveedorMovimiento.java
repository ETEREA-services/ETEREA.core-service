package eterea.core.service.hexagonal.proveedormovimiento.domain.model;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorMovimiento {

    private Long proveedorMovimientoId;
    private Integer empresaId;
    private Integer negocioId;
    private Long proveedorId;
    private Integer comprobanteId;
    private OffsetDateTime fechaComprobante;
    private OffsetDateTime fechaVencimiento;
    private Integer prefijo;
    private Long numeroComprobante;
    private BigDecimal importe;
    private BigDecimal cancelado;
    private BigDecimal aplicado;
    private BigDecimal neto;
    private BigDecimal netoCancelado;
    private BigDecimal montoIva;
    private BigDecimal montoIva27;
    private BigDecimal montoIva105;
    private BigDecimal percepcionIva;
    private BigDecimal percepcionIngresosBrutos;
    private BigDecimal gastosNoGravados;
    private BigDecimal ajustes;
    private OffsetDateTime fechaContable;
    private Integer ordenContable;
    private BigDecimal montoFacturadoC;
    private BigDecimal montoSujetoRetencionesIIBB;
    private BigDecimal montoRetencionesIIBB;
    private Long codigoRetencionesIIBB;
    private Long numeroComprobanteRetencionesIIBB;
    private String concepto;
    private Long cierreCajaId;
    private Integer nivel;
    private Integer negocioIdPaga;
    private Byte concursada;
    private BigDecimal importeConcursado;
    private OffsetDateTime fechaContableConcurso;
    private Integer ordenContableConcurso;
    private Byte marca;
    private Integer orden;
    private Byte transferida;

    private Comprobante comprobante;
    private Proveedor proveedor;
    private Negocio negocio;

    public String comprobanteKey() {
        return proveedorId + "." + comprobanteId + "." + prefijo + "." + numeroComprobante;
    }
}

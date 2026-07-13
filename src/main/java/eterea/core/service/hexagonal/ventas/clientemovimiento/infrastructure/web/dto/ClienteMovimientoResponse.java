package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto;

import eterea.core.service.hexagonal.comprobante.infrastructure.web.dto.ComprobanteResponse;
import eterea.core.service.hexagonal.empresa.infrastructure.web.dto.EmpresaResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteMovimientoResponse {

    private Long clienteMovimientoId;
    private Integer comprobanteId;
    private Integer puntoVenta;
    private Long numeroComprobante;
    private OffsetDateTime fechaComprobante;
    private Long clienteId;
    private Integer legajoId;
    private OffsetDateTime fechaVencimiento;
    private Integer negocioId;
    private Integer empresaId;
    private BigDecimal importe;
    private BigDecimal cancelado;
    private BigDecimal neto;
    private BigDecimal netoCancelado;
    private BigDecimal montoIva;
    private BigDecimal montoIvaRni;
    private BigDecimal reintegroTurista;
    private OffsetDateTime fechaContable;
    private Integer ordenContable;
    private Byte recibo;
    private Byte asignado;
    private Byte anulada;
    private Byte decreto104316;
    private String letraComprobante;
    private BigDecimal montoExento;
    private Long reservaId;
    private BigDecimal montoCuentaCorriente;
    private Long cierreCajaId;
    private Long cierreRestaurantId;
    private Integer nivel;
    private Byte eliminar;
    private Byte cuentaCorriente;
    private String letras;
    private String cae;
    private String caeVencimiento;
    private String codigoBarras;
    private BigDecimal participacion;
    private Integer monedaId;
    private BigDecimal cotizacion;
    private String observaciones;
    private String trackUuid;
    private Long clienteMovimientoIdSlave;

    private ComprobanteResponse comprobante;
    private ClienteResponse cliente;
    private MonedaResponse moneda;
    private EmpresaResponse empresa;
}

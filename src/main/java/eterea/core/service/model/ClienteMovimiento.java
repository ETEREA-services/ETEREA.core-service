package eterea.core.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Moneda;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "movclie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteMovimiento extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    private Long clienteMovimientoId;

    @Column(name = "cgocomprob")
    private Integer comprobanteId;

    @Column(name = "prefijo")
    @Builder.Default
    private Integer puntoVenta = 0;

    @Column(name = "nrocomprob")
    @Builder.Default
    private Long numeroComprobante = 0L;

    @Column(name = "fechacomprob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaComprobante;

    @Column(name = "cgoclie")
    @Builder.Default
    private Long clienteId = 0L;

    private Integer legajoId;

    @Column(name = "mcl_fechavenc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaVencimiento;

    @Column(name = "mcl_neg_id")
    @Builder.Default
    private Integer negocioId = 0;

    @Column(name = "mcl_emp_id")
    @Builder.Default
    private Integer empresaId = 0;

    @Builder.Default
    private BigDecimal importe = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal cancelado = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal neto = BigDecimal.ZERO;

    @Column(name = "netocancelado")
    @Builder.Default
    private BigDecimal netoCancelado = BigDecimal.ZERO;

    @Column(name = "montoiva")
    @Builder.Default
    private BigDecimal montoIva = BigDecimal.ZERO;

    @Column(name = "montoivarni")
    @Builder.Default
    private BigDecimal montoIvaRni = BigDecimal.ZERO;

    @Column(name = "reintegroturista")
    @Builder.Default
    private BigDecimal reintegroTurista = BigDecimal.ZERO;

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaContable;

    @Column(name = "nrocompconta")
    @Builder.Default
    private Integer ordenContable = 0;

    @Builder.Default
    private Byte recibo = 0;

    @Column(name = "mcl_asignado")
    @Builder.Default
    private Byte asignado = 0;

    @Builder.Default
    private Byte anulada = 0;

    @Builder.Default
    private Byte decreto104316 = 0;

    @Column(name = "tipocompro")
    private String letraComprobante;

    @Column(name = "montoexento")
    @Builder.Default
    private BigDecimal montoExento = BigDecimal.ZERO;

    @Column(name = "nroreserva")
    @Builder.Default
    private Long reservaId = 0L;

    @Column(name = "ctacte")
    @Builder.Default
    private BigDecimal montoCuentaCorriente = BigDecimal.ZERO;

    @Column(name = "mcl_cic_id")
    @Builder.Default
    private Long cierreCajaId = 0L;

    @Column(name = "mcl_cir_id")
    @Builder.Default
    private Long cierreRestaurantId = 0L;

    @Column(name = "mcl_nivel")
    @Builder.Default
    private Integer nivel = 0;

    @Column(name = "mcl_eliminar")
    @Builder.Default
    private Byte eliminar = 0;

    @Column(name = "mcl_ctacte")
    @Builder.Default
    private Byte cuentaCorriente = 0;

    @Column(name = "mcl_letras")
    @Builder.Default
    private String letras = "";

    @Column(name = "mcl_cae")
    @Builder.Default
    private String cae = "";

    @Column(name = "mcl_caevenc")
    @Builder.Default
    private String caeVencimiento = "";

    @Column(name = "mcl_barras")
    @Builder.Default
    private String codigoBarras = "";

    @Column(name = "mcl_particip")
    @Builder.Default
    private BigDecimal participacion = BigDecimal.ZERO;

    @Column(name = "mcl_mon_id")
    private Integer monedaId;

    @Column(name = "mcl_cotiz")
    @Builder.Default
    private BigDecimal cotizacion = BigDecimal.ZERO;

    private String observaciones;
    private String trackUuid;

    @Column(name = "clavev")
    @Builder.Default
    private Long clienteMovimientoIdSlave = 0L;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
    private Comprobante comprobante;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoclie", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToOne(optional = true)
    @JoinColumn(name = "mcl_mon_id", insertable = false, updatable = false)
    private Moneda moneda;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}

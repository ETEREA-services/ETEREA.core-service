package eterea.core.service.model;

import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
    name = "registrocae",
    uniqueConstraints = @UniqueConstraint(columnNames = {"rec_tco_id", "rec_prefijo", "rec_nrocomprob"})
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCae extends Auditable {

    @Id
    @Column(name = "rec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registroCaeId;

    @Column(name = "rec_tco_id")
    private Integer comprobanteId;

    @Column(name = "rec_prefijo")
    private Integer puntoVenta;

    @Column(name = "rec_nrocomprob")
    private Long numeroComprobante;

    @Column(name = "rec_cli_id")
    private Long clienteId;

    private Integer legajoId;

    @Column(name = "rec_cuit")
    private String cuit;

    @Column(name = "rec_total")
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "rec_exento")
    private BigDecimal exento = BigDecimal.ZERO;

    @Column(name = "rec_neto")
    private BigDecimal neto = BigDecimal.ZERO;

    @Column(name = "rec_neto105")
    private BigDecimal neto105 = BigDecimal.ZERO;

    @Column(name = "rec_iva")
    private BigDecimal iva = BigDecimal.ZERO;

    @Column(name = "rec_iva105")
    private BigDecimal iva105 = BigDecimal.ZERO;

    @Column(name = "rec_cae")
    private String cae;

    @Column(name = "rec_fecha")
    private String fecha;

    @Column(name = "rec_caevenc")
    private String caeVencimiento;

    @Column(name = "rec_barras")
    private String barras = "";

    private Integer tipoDocumento;
    private BigDecimal numeroDocumento = BigDecimal.ZERO;
    private Long clienteMovimientoIdAsociado;
    private String trackUuid;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}

package eterea.core.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.hexagonal.negocio.infrastructure.persistence.entity.NegocioEntity;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Cuenta;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "movcon", uniqueConstraints = @UniqueConstraint(columnNames = {"fecha", "nrocomp", "item"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaMovimiento extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaMovimientoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fecha;

    @Column(name = "nrocomp")
    @Builder.Default
    private Integer orden = 0;

    @Builder.Default
    private Integer item = 0;

    @Builder.Default
    private Byte debita = 0;

    @Column(name = "mco_neg_id")
    @Builder.Default
    private Integer negocioId = 0;

    @Column(name = "cuenta")
    @Builder.Default
    private Long numeroCuenta = 0L;

    @Column(name = "cgotcomp")
    @Builder.Default
    private Integer comprobanteId = 0;

    @Builder.Default
    private String concepto = "";

    @Builder.Default
    private BigDecimal importe = BigDecimal.ZERO;

    @Column(name = "cgosub")
    @Builder.Default
    private Long subrubroId = 0L;

    @Column(name = "cgoprov")
    @Builder.Default
    private Long proveedorId = 0L;

    @Column(name = "cgoclie")
    @Builder.Default
    private Long clienteId = 0L;

    private Integer legajoId;

    @Column(name = "mco_cic_id")
    @Builder.Default
    private Long cierreCajaId = 0L;

    @Column(name = "mco_nivel")
    @Builder.Default
    private Integer nivel = 0;

    @Column(name = "mco_mcf_firma")
    @Builder.Default
    private Long firma = 0L;

    @Column(name = "mco_tas_id")
    @Builder.Default
    private Integer tipoAsientoId = 0;

    @Column(name = "articulomovimiento_id")
    @Builder.Default
    private Long articuloMovimientoId = 0L;

    private Integer ejercicioId;

    @Builder.Default
    private Byte inflacion = 0;

    private String trackUuid;

    @OneToOne(optional = true)
    @JoinColumn(name = "cuenta", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Cuenta cuenta;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgotcomp", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Comprobante comprobante;

    @OneToOne(optional = true)
    @JoinColumn(name = "mco_neg_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private NegocioEntity negocio;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}

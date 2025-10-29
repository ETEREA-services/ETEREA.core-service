package eterea.core.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Cuenta;
import eterea.core.service.kotlin.model.Negocio;
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
    private Integer orden;

    private Integer item;
    private Byte debita = 0;

    @Column(name = "mco_neg_id")
    private Integer negocioId;

    @Column(name = "cuenta")
    private Long numeroCuenta;

    @Column(name = "cgotcomp")
    private Integer comprobanteId;

    private String concepto;
    private BigDecimal importe = BigDecimal.ZERO;

    @Column(name = "cgosub")
    private Long subrubroId;

    @Column(name = "cgoprov")
    private Long proveedorId = 0L;

    @Column(name = "cgoclie")
    private Long clienteId;

    private Integer legajoId;

    @Column(name = "mco_cic_id")
    private Long cierreCajaId = 0L;

    @Column(name = "mco_nivel")
    private Integer nivel = 0;

    @Column(name = "mco_mcf_firma")
    private Long firma = 0L;

    @Column(name = "mco_tas_id")
    private Integer tipoAsientoId = 0;

    @Column(name = "articulomovimiento_id")
    private Long articuloMovimientoId = 0L;

    private Integer ejercicioId;
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
    private Negocio negocio;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}

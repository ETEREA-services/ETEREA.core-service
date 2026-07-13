package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.entity.CuentaEntity;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifyable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "valores")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValorMovimientoEntity extends Auditable implements Jsonifyable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    private Long valorMovimientoId;

    @Column(name = "val_neg_id")
    private Integer negocioId;

    @Column(name = "codigo")
    private Integer valorId;

    @Column(name = "cgoprov")
    private Long proveedorId;

    @Column(name = "cgocli")
    private Long clienteId;

    @Column(name = "fechaemi")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaEmision;

    @Column(name = "fechavto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaVencimiento;

    @Column(name = "val_tco_id")
    private Integer comprobanteId;

    @Column(name = "nrocomprob")
    private Long numeroComprobante;

    @Builder.Default
    private BigDecimal importe = BigDecimal.ZERO;

    @Column(name = "cgocontable")
    private Long numeroCuenta;

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaContable;

    @Column(name = "nrocompcontable")
    @Builder.Default
    private Integer ordenContable = 0;

    @Column(name = "clavemovp")
    private Long proveedorMovimientoId;

    @Column(name = "clavemovv")
    private Long clienteMovimientoId;

    private String titular;

    private String banco;

    private String receptor;

    @Column(name = "cgoestado")
    private Integer estadoId;

    @Column(name = "fechaentrega")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaEntrega;

    @Builder.Default
    private Long tanda = 0L;

    @Column(name = "tandaindex")
    @Builder.Default
    private Long tandaIndex = 0L;

    @Column(name = "val_cic_id")
    private Long cierreCajaId;

    @Column(name = "val_nivel")
    @Builder.Default
    private Integer nivel = 0;

    private String observaciones;

    private String trackUuid;

    @OneToOne(optional = true)
    @JoinColumn(name = "codigo", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Valor valor;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocontable", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CuentaEntity cuenta;

}

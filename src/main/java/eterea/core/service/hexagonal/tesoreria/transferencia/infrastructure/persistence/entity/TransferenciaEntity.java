package eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.entity.ComprobanteEntity;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "transferencias", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tra_dneg_id", "tra_hneg_id", "tra_id"})
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    private Long transferenciaId;

    @Column(name = "tra_dneg_id")
    private Integer negocioIdDesde;

    @Column(name = "tra_hneg_id")
    private Integer negocioIdHasta;

    @Column(name = "tra_id")
    private Long numeroTransferencia;

    @Column(name = "tra_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fecha;

    @Column(name = "tra_total")
    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "tra_nrocontable")
    private Integer ordenContable;

    @Column(name = "tra_transferido")
    @Builder.Default
    private Byte transferido = 0;

    @Column(name = "tra_cmp_id")
    private Integer comprobanteId;

    @Column(name = "tra_fechaotro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaOtro;

    @OneToOne(optional = true)
    @JoinColumn(name = "tra_cmp_id", insertable = false, updatable = false)
    private ComprobanteEntity comprobante;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}

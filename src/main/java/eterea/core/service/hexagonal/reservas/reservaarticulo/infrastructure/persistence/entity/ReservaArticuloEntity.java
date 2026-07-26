package eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.entity;

import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.entity.ArticuloEntity;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifyable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "reservaarticulo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaArticuloEntity extends Auditable implements Jsonifyable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rar_id")
    private Long reservaArticuloId;

    @Column(name = "rar_neg_id")
    private Integer negocioId;

    @Column(name = "rar_res_id")
    private Long reservaId;

    @Column(name = "rar_vou_id")
    private Long voucherId;

    @Column(name = "rar_art_id")
    private String articuloId;

    @Builder.Default
    @Column(name = "rar_cantidad")
    private Integer cantidad = 0;

    @Builder.Default
    @Column(name = "rar_comision")
    private BigDecimal comision = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "rar_preciounitsincomision")
    private BigDecimal precioUnitarioSinComision = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "rar_preciounitario")
    private BigDecimal precioUnitario = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal precioUnitarioSinDescuento = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "rar_preciocompra")
    private BigDecimal precioCompra = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "rar_observaciones")
    private String observaciones = "";

    private String trackUuid;

    @OneToOne(optional = true)
    @JoinColumn(name = "rar_art_id", insertable = false, updatable = false)
    private ArticuloEntity articulo;

}

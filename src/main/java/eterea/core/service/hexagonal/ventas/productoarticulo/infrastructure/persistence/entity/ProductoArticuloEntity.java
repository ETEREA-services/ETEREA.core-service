package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.entity;

import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.entity.ArticuloEntity;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.model.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productoarticulo", uniqueConstraints = @UniqueConstraint(columnNames = {"par_prd_id", "par_art_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoArticuloEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_id")
    private Long productoarticuloId;

    @Column(name = "par_neg_id")
    private Integer negocioId;

    @Column(name = "par_prd_id")
    private Integer productoId;

    @Column(name = "par_art_id")
    private String articuloId;

    @OneToOne(optional = true)
    @JoinColumn(name = "par_prd_id", insertable = false, updatable = false)
    private Producto producto;

    @OneToOne(optional = true)
    @JoinColumn(name = "par_art_id", insertable = false, updatable = false)
    private ArticuloEntity articulo;

}

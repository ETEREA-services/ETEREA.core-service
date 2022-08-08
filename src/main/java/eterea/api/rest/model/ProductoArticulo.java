package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "productoarticulo", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "par_prd_id", "par_art_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductoArticulo extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2699717587778012930L;

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

}

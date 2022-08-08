/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "reservaarticulo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaArticulo extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8395636306427312580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rar_id")
	private Long reservaarticuloId;

	@Column(name = "rar_neg_id")
	@NotNull
	private Integer negocioId = 0;

	@Column(name = "rar_res_id")
	private Long reservaId;

	@Column(name = "rar_vou_id")
	private Long voucherId;

	@Column(name = "rar_art_id")
	@Size(max = 20)
	@NotNull
	private String articuloId = "";

	@Column(name = "rar_cantidad")
	@NotNull
	private Integer cantidad = 0;

	@Column(name = "rar_comision")
	@NotNull
	private BigDecimal comision = new BigDecimal(0);

	@Column(name = "rar_preciounitsincomision")
	@NotNull
	private BigDecimal preciounitariosincomision = new BigDecimal(0);

	@Column(name = "rar_preciounitario")
	@NotNull
	private BigDecimal preciounitario = new BigDecimal(0);

	@Column(name = "rar_preciocompra")
	@NotNull
	private BigDecimal preciocompra = new BigDecimal(0);

	@Column(name = "rar_observaciones")
	@Size(max = 250)
	@NotNull
	private String observaciones = "";

}

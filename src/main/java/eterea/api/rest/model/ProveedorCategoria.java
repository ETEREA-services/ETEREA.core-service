/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "provcategoria")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorCategoria extends Auditable implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6808411841943937094L;

	@Id
	@Column(name = "prc_id")
	private Integer proveedorCategoriaId;
	
	@Column(name = "prc_nombre")
	private String nombre;
	
	@Column(name = "prc_cva_id")
	private Integer valorId;
	
	@Column(name = "prc_exento")
	private BigDecimal exento = BigDecimal.ZERO;
}

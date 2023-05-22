/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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
@Table(name = "moneda")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Moneda extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7521067423582119467L;

	@Id
	@Column(name = "mon_id")
	private Integer monedaId;

	@Column(name = "mon_nombre")
	@NotNull
	private String nombre = "";

	@Column(name = "mon_simbolo")
	@NotNull
	private String simbolo = "";

	@Column(name = "mon_cva_id")
	private Integer valorId;

}

/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "habitaciontipo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionTipo extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6362543514624729735L;

	@Id
	@Column(name = "hat_id")
	private Integer habitacionTipoId;

	@Column(name = "hat_nombre")
	private String nombre;

	@Column(name = "hat_paxs")
	private Integer paxs;

}

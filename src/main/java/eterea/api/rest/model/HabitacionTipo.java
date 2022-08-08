/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

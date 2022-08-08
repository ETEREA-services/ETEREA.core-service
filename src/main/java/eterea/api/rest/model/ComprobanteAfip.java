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
@Table(name = "compafip")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteAfip extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -5540166718694408596L;

	@Id
	@Column(name = "caf_id")
	private Integer comprobanteAfipId;

	@Column(name = "caf_nombre")
	private String nombre;

	@Column(name = "caf_label")
	private String label;

}

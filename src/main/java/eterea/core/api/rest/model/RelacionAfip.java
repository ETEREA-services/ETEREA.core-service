/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "relacafip")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RelacionAfip extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1587731706341947513L;

	@Id
	private Integer relacionId;

	private String nombre;
	private Long autoId;

}

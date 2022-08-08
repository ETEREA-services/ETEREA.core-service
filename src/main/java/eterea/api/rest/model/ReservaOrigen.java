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
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "reservaorigen")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaOrigen extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2466556281350498369L;

	@Id
	@Column(name = "reo_id")
	private Integer reservaOrigenId;
	
	@Column(name = "reo_nombre")
	private String nombre;

}

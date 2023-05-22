/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alma
 *
 */
@Entity
@Table(name = "reservaorigen")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
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

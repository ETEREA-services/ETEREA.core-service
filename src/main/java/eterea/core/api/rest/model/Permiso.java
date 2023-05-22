/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "permisos")
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Permiso extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -113768742598781178L;

	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permisoId;

	private String nombre;
	private String opcion;
	private Byte autorizado = (byte) 0;

}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
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
@Table(name = "posicion")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Posicion extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2897246130236789300L;

	@Id
	@Column(name = "posicion_id")
	private Integer posicionId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "user_id")
	private String userId;
}

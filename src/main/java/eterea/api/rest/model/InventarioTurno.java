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
@Table(name = "inventarioturno")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InventarioTurno extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -6676157716119705159L;

	@Id
	@Column(name = "int_id")
	private Integer inventarioTurnoId;
	
	@Column(name = "int_nombre")
	private String nombre;
	
}

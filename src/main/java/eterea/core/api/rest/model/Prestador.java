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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "prestador")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Prestador extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6268369828942305068L;

	@Id
	@Column(name = "pre_id")
	private Integer prestadorId; 
	
	@Column(name = "pre_nombre")
	private String nombre;
}

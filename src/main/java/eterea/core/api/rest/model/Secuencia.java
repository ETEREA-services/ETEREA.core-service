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
@Table(name = "secuencia")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Secuencia extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5620519269761351013L;

	@Id
	@Column(name = "sec_id")
	private Integer secuenciaId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "sec_neg_id")
	private Integer negocioId;
}

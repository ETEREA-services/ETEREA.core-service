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

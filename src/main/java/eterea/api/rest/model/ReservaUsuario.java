/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(name = "reservausuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "rus_res_id", "rus_usuario" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaUsuario extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5778832364903182847L;

	@Column(name = "rus_res_id")
	private Long reservaId;
	
	@Column(name = "rus_usuario")
	private String usuario;
	
	@Id
	@Column(name = "rus_id")
	private Long reservaUsuarioId;
}

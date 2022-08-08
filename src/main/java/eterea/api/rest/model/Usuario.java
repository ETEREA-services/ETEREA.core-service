/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3577203074453281119L;

	@Id
	@Column(name = "nombre")
	@Size(max = 20)
	private String login;

	@Column(name = "usu_descripcion")
	@Size(max = 100)
	private String descripcion;

	@Column(name = "usu_password")
	@Size(max = 100)
	private String password;

	@Column(name = "usu_correo")
	@Size(max = 250)
	private String email;

	@Column(name = "usu_nivel")
	private Integer nivel;

	@Column(name = "usu_pin")
	@Size(max = 4)
	private String pin;

	@Column(name = "usu_cierrectype")
	@Size(max = 5)
	private String cierreRecipientType;

	@Column(name = "usu_consrectype")
	@Size(max = 5)
	private String consolidadoRecipientType;

	@Column(name = "clave")
	private Long usuarioId;
	
}

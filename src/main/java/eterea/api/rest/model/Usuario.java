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
	private String login;

	@Column(name = "usu_descripcion")
	private String descripcion;

	@Column(name = "usu_password")
	private String password;

	@Column(name = "usu_correo")
	private String email;

	@Column(name = "usu_nivel")
	private Integer nivel;

	@Column(name = "usu_pin")
	private String pin;

	@Column(name = "usu_cierrectype")
	private String cierreRecipientType;

	@Column(name = "usu_consrectype")
	private String consolidadoRecipientType;

	@Column(name = "clave")
	private Long usuarioId;
	
}

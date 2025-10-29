/**
 * 
 */
package eterea.core.service.model.view;

import java.io.Serializable;
import java.time.OffsetDateTime;

import eterea.core.service.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Immutable
@Table(name = "vw_usuariovencimiento")
@EqualsAndHashCode(callSuper = false)
public class UsuarioVencimiento extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7372254888843378575L;

	@Id
	private String login;

	private String descripcion;
	private String password;
	private String email;
	private Integer nivel;
	private String pin;

	@Column(name = "cierrerecipienttype")
	private String cierreRecipientType;

	@Column(name = "consolidadorecipienttype")
	private String consolidadoRecipientType;

	@Column(name = "usuarioID")
	private Long usuarioId;

	@Column(name = "fechavencimiento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;
}

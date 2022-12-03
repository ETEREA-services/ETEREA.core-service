/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "usuarioactivo", uniqueConstraints = { @UniqueConstraint(columnNames = { "uac_ip", "uac_hwnd" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioActivo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1373842300964038512L;

	@Column(name = "uac_ip")
	private String ipAddress;
	
	@Column(name = "uac_hwnd")
	private Long hWnd;
	
	@Column(name = "uac_login")
	private String login;
	
	@Column(name = "uac_timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime timestamp;
	
	@Column(name = "uac_actualizacion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime actualizacion;
	
	@Id
	@Column(name = "uac_id")
	private Long usuarioActivoId; 
}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "reservacomentario")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaComentario extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2388905330353120208L;

	@Id
	@Column(name = "clave")
	private Long reservaComentarioId;
	
	@Column(name = "rec_res_id")
	private Long reservaId;
	
	@Column(name = "rec_timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime timestamp;
	
	@Column(name = "rec_usuario")
	private String usuario;
	
	@Column(name = "rec_comentario")
	private String comentario;
}

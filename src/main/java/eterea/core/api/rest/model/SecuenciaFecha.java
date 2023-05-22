/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "secuenciafecha")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SecuenciaFecha extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4476616504060185570L;

	@Id
	@Column(name = "clave")
	private Long secuenciaFechaId;
	
	@Column(name = "sfe_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "sfe_sec_id")
	private Integer secuenciaId;
}

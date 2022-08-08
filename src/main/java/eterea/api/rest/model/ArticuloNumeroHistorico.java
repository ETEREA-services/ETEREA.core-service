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
@Table(name = "articulonumeroh")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloNumeroHistorico extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4185254272096941623L;

	@Id
	@Column(name = "anh_id")
	private Long articuloNumeroHistoricoId;
	
	@Column(name = "anh_art_id")
	private String articuloId;
	
	@Column(name = "anh_serie")
	private String serie;
	
	@Column(name = "anh_numero")
	private Long numero;
	
	@Column(name = "anh_estado")
	private String estado;
	
	@Column(name = "anh_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
}

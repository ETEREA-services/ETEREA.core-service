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
import javax.persistence.UniqueConstraint;

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
@Table(name = "articuloservicio", uniqueConstraints = { @UniqueConstraint(columnNames = { "ars_art_id", "ars_ser_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloServicio extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2394965490884103985L;

	@Column(name = "ars_art_id")
	private String articuloId;
	
	@Column(name = "ars_ser_id")
	private Integer servicioId;
	
	@Column(name = "ars_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "ars_orden")
	private Integer orden;
	
	@Id
	@Column(name = "ars_id")
	private Long articuloServicioId;

}

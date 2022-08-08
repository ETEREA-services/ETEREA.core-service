/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cotizacionfecha", uniqueConstraints = { @UniqueConstraint(columnNames = { "cof_cot_id", "cof_fecha" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionFecha extends Auditable implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2140008268614128510L;

	@Id
	@Column(name = "cof_cot_id")
	private Integer cotizacionId;
	
	@Column(name = "cof_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "cof_cotizacion")
	private BigDecimal cotizacion = BigDecimal.ZERO;
	
	@Column(name = "clave")
	private Long cotizacionFechaId;
}

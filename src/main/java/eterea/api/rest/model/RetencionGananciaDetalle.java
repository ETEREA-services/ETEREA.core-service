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
@Table(name = "retgciasd", uniqueConstraints = { @UniqueConstraint(columnNames = { "rgd_val_clave", "rgd_tipo", "rgd_clave" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RetencionGananciaDetalle extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4484763953023899879L;

	@Column(name = "rgd_val_clave")
	private Long valorMovimientoId;
	
	@Column(name = "rgd_tipo")
	private String tipo;
	
	@Column(name = "rgd_clave")
	private Long clave;
	
	@Column(name = "rgd_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Id
	@Column(name = "rgd_id")
	private Long retencionGanaciaDetalleId;
}

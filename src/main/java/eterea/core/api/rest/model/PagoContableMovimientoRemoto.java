/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "movcpagosremoto", uniqueConstraints = { @UniqueConstraint(columnNames = { "mcr_neg_id", "mcr_tco_id", "mcr_orden" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PagoContableMovimientoRemoto extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4346947038379651494L;

	@Column(name = "mcr_neg_id")
	private Integer negocioId;
	
	@Column(name = "mcr_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "mcr_orden")
	private Long orden;
	
	@Column(name = "mcr_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "mcr_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mcr_nrocontable")
	private Integer numeroContable;
	
	@Column(name = "mcr_cgocontable")
	private Long cuenta;
	
	@Id
	@Column(name = "mcr_id")
	private Long pagoContableMovimientoRemotoId;
}

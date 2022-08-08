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
@Table(name = "movcpagos", uniqueConstraints = { @UniqueConstraint(columnNames = { "mcp_tco_id", "mcp_orden" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PagoContableMovimiento extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8905170845580614380L;

	@Column(name = "mcp_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "mcp_orden")
	private Long orden;
	
	@Column(name = "mcp_neg_id")
	private Integer negocioId;
	
	@Column(name = "mcp_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "mcp_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mcp_nrocontable")
	private Integer ordenContable;
	
	@Column(name = "mcp_cgocontable")
	private Long cuenta;
	
	@Id
	@Column(name = "mcp_id")
	private Long pagoContableMovimientoId;
	
}

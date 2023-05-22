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
@Table(name = "stockmovs", uniqueConstraints = { @UniqueConstraint(columnNames = { "stm_dea_id", "stm_stf_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StockMovs extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1724920517638538017L;

	@Column(name = "stm_dea_id")
	private Long articuloMovimientoId;
	
	@Column(name = "stm_stf_id")
	private Long stockFichaId;
	
	@Column(name = "stm_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "stm_cantidad")
	private BigDecimal cantidad = BigDecimal.ZERO;
	
	@Id
	@Column(name = "stm_id")
	private Long stockMovimientosId;
}

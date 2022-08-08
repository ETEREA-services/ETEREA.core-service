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
@Table(name = "ocompra")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompra extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -38812335196983595L;

	@Id
	@Column(name = "oco_id")
	private Long ordenCompraId;
	
	@Column(name = "oco_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "oco_res_id")
	private Long reservaId;
	
	@Column(name = "oco_mcl_id")
	private Long clienteMovimientoId;
	
	@Column(name = "oco_total")
	private BigDecimal total = BigDecimal.ZERO;
}

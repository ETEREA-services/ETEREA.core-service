/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "tarifashabitaciones")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TarifaHabitacion extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4915151714921736187L;

	@Id
	@Column(name = "codigo")
	private Long tarifaHabitacionId;

	private String descripcion;
	private BigDecimal precio = BigDecimal.ZERO;

	@Column(name = "bloqueofactura")
	private Byte bloqueoFactura;

	@Column(name = "minimoanticipo")
	private BigDecimal minimoAnticipo = BigDecimal.ZERO;

}

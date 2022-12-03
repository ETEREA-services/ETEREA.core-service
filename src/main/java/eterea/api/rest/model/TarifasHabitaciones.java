/**
 * 
 */
package eterea.api.rest.model;

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
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "tarifashabitaciones")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TarifasHabitaciones extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -8087286210021732084L;

	@Id
	private Long codigo;
	private String descripcion;
	private BigDecimal precio = BigDecimal.ZERO;
	
	@Column(name = "bloqueofactura")
	private byte bloqueoFactura;
	
	@Column(name = "minimoanticipo")
	private BigDecimal minimoAnticipo = BigDecimal.ZERO;
}

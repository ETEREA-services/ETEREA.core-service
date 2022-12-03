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
@Table(name = "reservaacomp")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaAcompanante extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8296803683567599614L;

	@Id
	@Column(name = "reserva_id")
	private Long reservaAcompananteId;
	
	@Column(name = "nro_documento")
	private BigDecimal numeroDocumento = BigDecimal.ZERO;
	
}

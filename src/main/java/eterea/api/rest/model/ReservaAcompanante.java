/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

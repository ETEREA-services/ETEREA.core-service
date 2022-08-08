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
@Table
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CoeficienteInflacion extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3297333167839333107L;

	private Integer anho;
	private Integer mes;
	private BigDecimal coeficiente = BigDecimal.ZERO;

	@Id
	@Column(name = "coeficienteinflacion_id")
	private Long coeficienteInflacionId;
	
}

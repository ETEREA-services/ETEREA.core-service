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
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "monedadenominacion")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MonedaDenominacion extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742005664026481602L;

	@Id
	@Column(name = "mde_mon_id")
	private Integer monedaId;
	
	@Column(name = "mde_denominacion")
	private BigDecimal denominacion = BigDecimal.ZERO;
	
}

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
@Table(name = "conversion")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Conversion extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3371459795141093799L;

	@Id
	@Column(name = "con_id")
	private Integer conversion_id;
	
	@Column(name = "con_descripcion")
	private String descripcion;
	
	@Column(name = "con_indice")
	private BigDecimal indice = BigDecimal.ZERO;
	
}

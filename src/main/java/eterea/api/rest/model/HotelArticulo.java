/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(name = "hotelarticulo", uniqueConstraints = { @UniqueConstraint(columnNames = { "har_hot_id", "har_art_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HotelArticulo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8211435366556804815L;

	@Column(name = "har_hot_id")
	private Long hotelId;
	
	@Column(name = "har_art_id")
	private String articuloId;
	
	@Column(name = "har_comision")
	private BigDecimal comision = BigDecimal.ZERO;
	
	@Id
	@Column(name = "har_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelArticuloId;

}

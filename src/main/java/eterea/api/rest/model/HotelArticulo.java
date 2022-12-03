/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


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

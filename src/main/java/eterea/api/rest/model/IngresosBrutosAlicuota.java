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
@Table(name = "ibalicuota", uniqueConstraints = { @UniqueConstraint(columnNames = { "ibcontribuyente_id", "ibcategoria_id", "posicionId" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class IngresosBrutosAlicuota extends Auditable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775341202700317561L;

	@Column(name = "ibcontribuyente_id")
	private Integer ingresosBrutosContribuyenteId;
	
	@Column(name = "ibcategoria_id")
	private Integer ingresosBrutosCategoria_Id;
	
	private Integer posicionId;
	private BigDecimal base = BigDecimal.ZERO;
	private BigDecimal alicuota = BigDecimal.ZERO;
	
	@Column(name = "cgovalor_id")
	private Integer valorId;
	
	@Id
	@Column(name = "auto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingresosBrutosAlicuotaId;
	
	private String userId;
	
}

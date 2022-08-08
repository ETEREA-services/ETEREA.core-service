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
@Table(name = "factpagadas", uniqueConstraints = { @UniqueConstraint(columnNames = { "clavemovp", "clavepago" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class FacturaPagada extends Auditable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2041284355956741253L;

	@Column(name = "clavemovp")
	private Long proveedorMovimientoId;
	
	@Column(name = "clavepago")
	private Long proveedorMovimientoIdPago;
	
	private BigDecimal importe = BigDecimal.ZERO;
	private BigDecimal neto = BigDecimal.ZERO;
	
	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long facturaPagadaId;
	
}

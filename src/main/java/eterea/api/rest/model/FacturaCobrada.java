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
@Table(name = "factcobradas", uniqueConstraints = { @UniqueConstraint(columnNames = { "clavemovc", "clavepago" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class FacturaCobrada extends Auditable implements Serializable  {
		/**
	 * 
	 */
	private static final long serialVersionUID = -5578766082421599388L;

	@Column(name = "clavemovc")
	private Long clienteMovimientoId;
		
	@Column(name = "clavepago")
	private Long clienteMovimientoIdPago;
		
	private BigDecimal importe = BigDecimal.ZERO;
		
	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer facturaCobradaId;
		
	@Column(name = "marca")
	private Byte marca;
		
}

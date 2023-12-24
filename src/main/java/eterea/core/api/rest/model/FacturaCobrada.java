/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import eterea.core.api.rest.kotlin.model.Auditable;
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

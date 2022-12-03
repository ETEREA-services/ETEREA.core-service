/**
 * 
 */
package eterea.api.rest.model;

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
@Table(name = "plancta")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6989170008865614209L;

	@Id
	private Long cuenta;
	
	private String nombre;
	
	@Column(name = "pla_neg_id")
	private Integer negocioId;
	
	private Byte integra;
	
	@Column(name = "pla_transfiere")
	private Byte transfiere;
	
	@Column(name = "pla_movstock")
	private Byte movimientoStock;
	
	@Column(name = "pla_cuentamaestro")
	private BigDecimal cuentaMaestro = BigDecimal.ZERO;
	
	@Column(name = "pla_grado")
	private Integer grado;
	
	@Column(name = "pla_grado1")
	private Long grado1;
	
	@Column(name = "pla_grado2")
	private Long grado2;
	
	@Column(name = "pla_grado3")
	private Long grado3;
	
	@Column(name = "pla_grado4")
	private Long grado4;
	
	@Column(name = "pla_ventas")
	private Byte ventas;
	
	@Column(name = "pla_compras")
	private Byte compras;
	
	@Column(name = "pla_gastos")
	private Byte gastos;
	
}

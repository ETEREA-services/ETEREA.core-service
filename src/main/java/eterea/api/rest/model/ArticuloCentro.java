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
@Table(name = "articuloscentro", uniqueConstraints = { @UniqueConstraint(columnNames = { "arc_art_id", "arc_cst_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloCentro extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 7150802832685023175L;

	@Column(name = "arc_art_id")
	private String articuloId;
	
	@Column(name = "arc_cst_id")
	private Integer centroStockId;
	
	@Column(name = "arc_saldo")
	private BigDecimal saldo = BigDecimal.ZERO;
	
	@Column(name = "saldosf")
	private BigDecimal saldoStockFicha = BigDecimal.ZERO;
	
	@Id
	@Column(name = "arc_id")
	private Long articuloCentroId;
	
}

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
@Table(name = "productoclientecomision", uniqueConstraints = { @UniqueConstraint(columnNames = { "pcc_prd_id", "pcc_cli_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductoClienteComision extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3454572158713255519L;

	@Column(name = "pcc_prd_id")
	private Integer productoId;
	
	@Column(name = "pcc_cli_id")
	private Long clienteId;
	
	@Column(name = "pcc_neg_id")
	private Integer negocioId;
	
	@Column(name = "pcc_comision")
	private BigDecimal comision = BigDecimal.ZERO;
	
	@Id
	@Column(name = "pcc_id")
	private Long productoClienteComisionId;
}

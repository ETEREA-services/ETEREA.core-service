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
@Table(name = "tmbalance")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BalanceTemporal extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5812247741562102064L;

	@Id
	@Column(name = "clave")
	private Long balanceTemporalId;
	
	@Column(name = "bal_hwnd")
	private Long hWnd;
	
	@Column(name = "bal_neg_id")
	private Integer negocioId;
	
	@Column(name = "bal_cuenta")
	private Long cuenta;
	
	@Column(name = "bal_deudor")
	private BigDecimal deudor = BigDecimal.ZERO;
	
	@Column(name = "bal_acreedor")
	private BigDecimal acreedor = BigDecimal.ZERO;
	
	@Column(name = "bal_saldodeudor")
	private BigDecimal saldoDeudor = BigDecimal.ZERO;
	
	@Column(name = "bal_saldoacreedor")
	private BigDecimal saldoAcreedor = BigDecimal.ZERO;
	
	@Column(name = "bal_grado")
	private Integer grado;
}

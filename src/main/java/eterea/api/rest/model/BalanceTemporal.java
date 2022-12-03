/**
 * Entidad de la tabla tmbalance, que contiene los datos de un balance temporal
 * se estima de uso constante del area contable, la tabla presenta inconsistencias
 * en la clave primaria
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tmbalance")
@AllArgsConstructor
public class BalanceTemporal extends Auditable implements Serializable {

	@Serial
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BalanceTemporal that = (BalanceTemporal) o;
		return balanceTemporalId != null && Objects.equals(balanceTemporalId, that.balanceTemporalId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

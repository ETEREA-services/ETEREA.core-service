/**
 * Entidad vinculada a articuloscentro ...hay inconsistencia en la tabla un salto temoporal en
 * los registros y cambio en la nomenclatura al 19-05-2022, no registra un uso diario
 */
package eterea.core.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articuloscentro", uniqueConstraints = { @UniqueConstraint(columnNames = { "arc_art_id", "arc_cst_id" }) })
@AllArgsConstructor
public class ArticuloCentro extends Auditable implements Serializable {
	@Serial
	private static final long serialVersionUID = 71508028326850175L;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloCentro that = (ArticuloCentro) o;
		return articuloCentroId != null && Objects.equals(articuloCentroId, that.articuloCentroId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

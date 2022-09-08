/**
 * Consolidado caja entidad relacionada a la tabla conscaja ultimo registro 28/04/2019
 * TODO: Revisar uso ern el modelo
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "conscaja", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "cca_neg_id", "cca_cic_id", "cca_fecha", "cca_cuenta" }) })
@AllArgsConstructor
public class ConsolidadoCaja extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -1794105858634705366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consolidadoCajaId;

	@Column(name = "cca_neg_id")
	private Integer negocioId;

	@Column(name = "cca_cic_id")
	private Long cierreCajaId;

	@Column(name = "cca_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "cca_cuenta")
	private Long cuenta;

	@Column(name = "cca_deudor")
	private BigDecimal deudor = BigDecimal.ZERO;

	@Column(name = "cca_acreedor")
	private BigDecimal acreedor = BigDecimal.ZERO;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ConsolidadoCaja that = (ConsolidadoCaja) o;
		return consolidadoCajaId != null && Objects.equals(consolidadoCajaId, that.consolidadoCajaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

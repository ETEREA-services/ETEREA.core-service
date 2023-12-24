/**
 * Entidad relacionada a la tabla conceptosfact,
 *
 */
package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "conceptosfact")
@AllArgsConstructor
public class ConceptoFacturado extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 8264376062048795530L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long conceptoFacturadoId;

	@Column(name = "clavemovclie")
	private Long clienteMovimientoId;

	@Column(name = "nrolinea")
	private Integer numeroLinea;

	@Column(name = "concepto")
	@NotNull
	@Size(max = 240)
	private String concepto = "";

	@Column(name = "clavedetartic")
	private Long articuloMovimientoId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ConceptoFacturado that = (ConceptoFacturado) o;
		return conceptoFacturadoId != null && Objects.equals(conceptoFacturadoId, that.conceptoFacturadoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

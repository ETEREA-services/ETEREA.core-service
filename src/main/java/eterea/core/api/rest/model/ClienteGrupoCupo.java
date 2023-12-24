/**
 * Entidad de la tabla clientegrupocupo
 */
package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "clientegrupocupo", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "cliente_id", "grupo_id", "dias" }) })
@AllArgsConstructor
public class ClienteGrupoCupo extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -6836097066483666183L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clientegrupocupo_id")
	private Long clientegrupocupoId;

	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "grupo_id")
	private Integer grupoId;

	@Column(name = "dias")
	@NotNull
	private Integer dias = 0;

	@Column(name = "cantidad")
	@NotNull
	private Integer cantidad = 0;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ClienteGrupoCupo that = (ClienteGrupoCupo) o;
		return clientegrupocupoId != null && Objects.equals(clientegrupocupoId, that.clientegrupocupoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

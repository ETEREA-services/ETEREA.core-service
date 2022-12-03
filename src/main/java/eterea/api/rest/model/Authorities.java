/**
 * Entidad de la tabla authorities, hace las veces de contenedor de roles de usuario
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "authorities", uniqueConstraints = { @UniqueConstraint(columnNames = { "cli_internet_id" }) })
@AllArgsConstructor
public class Authorities implements Serializable {

	@Serial
	private static final long serialVersionUID = -5365067307934008404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "cli_internet_id")
	private Long clienteId;

	@Column(name = "authority")
	private String authority;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Authorities that = (Authorities) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

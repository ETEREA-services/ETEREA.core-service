/**
 * Entidad de la tabla clientecateg
 */
package eterea.core.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "clientecateg")
@AllArgsConstructor
public class ClienteCategoria extends Auditable implements Serializable {

	private static final long serialVersionUID = -2812112161256002721L;

	@Id
	@Column(name = "cca_id")
	private Integer clienteCategoriaId;
	
	@Column(name = "cca_nombre")
	private String nombre;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ClienteCategoria that = (ClienteCategoria) o;
		return clienteCategoriaId != null && Objects.equals(clienteCategoriaId, that.clienteCategoriaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

/**
 * Entidad de la tabla clienteinternet
 * TO DO: el tipo de cliente deberia ser una atributo de la clase cliente,
 * o refatorizar a un modelo de usuario de aplicativo web, como paso previo y una
 * vez realice alguna compra pasar a ser cliente
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "clienteinternet")
@AllArgsConstructor
public class ClienteInternet extends Auditable implements Serializable {

	private static final long serialVersionUID = -7817956435234734227L;

	@Id
	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "password")
	@Size(max = 64)
	private String password;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ClienteInternet that = (ClienteInternet) o;
		return clienteId != null && Objects.equals(clienteId, that.clienteId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

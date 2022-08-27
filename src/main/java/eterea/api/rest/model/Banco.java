/**
 * Entidad de la tabla Banco
 * Revisar: Entidad sin registros, podria ser reemplazada por un enum
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "banco")
@AllArgsConstructor
public class Banco extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -4578943377018812826L;

	@Id
	@Column(name = "ban_id")
	private Integer bancoId;
	
	@Column(name = "ban_nombre")
	private String nombre;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Banco banco = (Banco) o;
		return bancoId != null && Objects.equals(bancoId, banco.bancoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

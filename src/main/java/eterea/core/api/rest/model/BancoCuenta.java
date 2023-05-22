/**
 * Entidad de la tabla bancocuenta, podria ser nominada como cuentabancaria y poseer en sus atributos
 * un objeto de tipo Banco en una relacion One to One, facilitando su tratamiento en controlador y servicio.
 */
package eterea.core.api.rest.model;

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
@Table(name = "bancocuenta", uniqueConstraints = { @UniqueConstraint(columnNames = { "bcu_ban_id", "bcu_cuenta" }) })
@AllArgsConstructor
public class BancoCuenta extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 3385001732647319275L;

	@Column(name = "bcu_ban_id")
	private Integer bancoId;
	
	@Column(name = "bcu_cuenta")
	private String cuenta;
	
	@Column(name = "bcu_neg_id")
	private Integer negocioId;
	
	@Column(name = "bcu_titular")
	private String titular;
	
	@Column(name = "bcu_cbu")
	private String cbu;
	
	@Id
	@Column(name = "clave")
	private Integer bancoCuentaId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BancoCuenta that = (BancoCuenta) o;
		return bancoCuentaId != null && Objects.equals(bancoCuentaId, that.bancoCuentaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

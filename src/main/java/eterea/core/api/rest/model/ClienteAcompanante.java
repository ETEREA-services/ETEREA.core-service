/**
 * Entidad relacionada a la tabla clienteacomp, sin relacion a clientes
 * TOD DO: pensar en la figura de PAX o pasajero para indicar que un cliente puede
 * tener una lista de pasajeros que lo acompañan, sin llegarse a mezclar con los grupos
 * un cliente que paga el servicio y su familia que lo acompaña, no llega a ser un grupo
 */
package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
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
@Table(name = "clienteacomp", uniqueConstraints = { @UniqueConstraint(columnNames = { "cliente_id", "nro_documento" }) })
@AllArgsConstructor
public class ClienteAcompanante extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -9052145349621042869L;

	@Id
	@Column(name = "cliente_id")
	private Long clienteId;
	
	@Column(name = "nro_documento")
	private BigDecimal numeroDocumento = BigDecimal.ZERO;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "auto_id")
	private Long clienteAcompanateId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ClienteAcompanante that = (ClienteAcompanante) o;
		return clienteId != null && Objects.equals(clienteId, that.clienteId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

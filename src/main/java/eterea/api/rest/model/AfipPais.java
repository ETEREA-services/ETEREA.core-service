/**
 * Entidad AfipPais relacionada a la tabla paisafip que guarda
 *
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "paisafip")
@NoArgsConstructor
@AllArgsConstructor
public class AfipPais extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493795348076945340L;

	@Id
	@Column(name = "pais_id")
	private Integer afipPaisId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "auto_id")
	private Long autoId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		AfipPais afipPais = (AfipPais) o;
		return afipPaisId != null && Objects.equals(afipPaisId, afipPais.afipPaisId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

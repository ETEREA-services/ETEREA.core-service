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
@Table(name = "compafip")
@AllArgsConstructor
public class ComprobanteAfip extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -5540166718694408596L;

	@Id
	@Column(name = "caf_id")
	private Integer comprobanteAfipId;

	@Column(name = "caf_nombre")
	private String nombre;

	@Column(name = "caf_label")
	private String label;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ComprobanteAfip that = (ComprobanteAfip) o;
		return comprobanteAfipId != null && Objects.equals(comprobanteAfipId, that.comprobanteAfipId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

/**
 * 
 */
package eterea.core.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Asiento extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -6398935299606790650L;

	@Id
	@Column(name = "asi_id")
	private BigDecimal asientoId;
	
	@Column(name = "asi_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "asi_usuario")
	private String usuario;
	
	@Column(name = "asi_ip")
	private String ipAdress;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Asiento asiento = (Asiento) o;
		return asientoId != null && Objects.equals(asientoId, asiento.asientoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

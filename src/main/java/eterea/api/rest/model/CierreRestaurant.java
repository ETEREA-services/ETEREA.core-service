/**
 * To Do: Revisar Tabla sin registros
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cierrerest")
@AllArgsConstructor
public class CierreRestaurant extends Auditable implements Serializable {

	private static final long serialVersionUID = -1751024413942491962L;

	@Id
	@Column(name = "cir_id")
	private Long cierreRestaurantId;
	
	@Column(name = "cir_neg_id")
	private Integer negocioId;
	
	@Column(name = "cir_fechahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHora;
	
	@Column(name = "cir_usuario")
	private String usuario;
	
	@Column(name = "cir_nivel")
	private Integer nivel;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CierreRestaurant that = (CierreRestaurant) o;
		return cierreRestaurantId != null && Objects.equals(cierreRestaurantId, that.cierreRestaurantId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

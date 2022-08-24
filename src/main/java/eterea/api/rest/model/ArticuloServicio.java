/**
 * Entidad de la tabla articuloservicio, que parece ser una tabla union entre Servicios y Articulos
 * podria ser cambiada por una lista de articulos en la entidad Servicio con una etiqueta @JoinTable
 * y el registro de la fecha que quede del lado del Servicio
 * Nota: se deja bajo comentario en la Clase Servicio la solucion planteada
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articuloservicio", uniqueConstraints = { @UniqueConstraint(columnNames = { "ars_art_id", "ars_ser_id" }) })
@AllArgsConstructor
public class ArticuloServicio extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -2394965490884103985L;

	@Column(name = "ars_art_id")
	private String articuloId;
	
	@Column(name = "ars_ser_id")
	private Integer servicioId;
	
	@Column(name = "ars_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "ars_orden")
	private Integer orden;
	
	@Id
	@Column(name = "ars_id")
	private Long articuloServicioId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloServicio that = (ArticuloServicio) o;
		return articuloServicioId != null && Objects.equals(articuloServicioId, that.articuloServicioId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

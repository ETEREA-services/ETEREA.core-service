/**
 * REVISAR: Entidad de la tabla articulohabil...sin registros en la BD
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
@Table(name = "articulohabil", uniqueConstraints = { @UniqueConstraint(columnNames = { "aha_fecha", "aha_serie" , "aha_inicial" }) })
@AllArgsConstructor
public class ArticuloHabilitado extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 908939469839172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "aha_id")
	private Long articuloHabilitadoId;
	
	@Column(name = "aha_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHabilitacion;
	
	@Column(name = "aha_serie")
	private String serie;
	
	@Column(name = "aha_inicial")
	private Long numeroInicial;
	
	@Column(name = "aha_final")
	private Long numeroFinal;
	
	@Column(name = "aha_art_id")
	private String articuloId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloHabilitado that = (ArticuloHabilitado) o;
		return articuloHabilitadoId != null && Objects.equals(articuloHabilitadoId, that.articuloHabilitadoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

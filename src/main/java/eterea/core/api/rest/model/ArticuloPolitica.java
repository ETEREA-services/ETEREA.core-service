/**
 * Entidad de la tabla articulopolitica,
 * Revisar integridad registros con campos NULL
 * Nota Personal: se necesita mas análisis para entendimiento
 */
package eterea.core.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articulopolitica", uniqueConstraints = { @UniqueConstraint(columnNames = { "arp_art_id", "arp_pol_id" }) })
@AllArgsConstructor
public class ArticuloPolitica extends Auditable implements Serializable {

	 @Serial
	 private static final long serialVersionUID = 1375278222938563810L;
	 
	@Column(name = "arp_art_id")
	private String articuloId;
	
	@Column(name = "arp_pol_id")
	private Integer politicaId;
	
	@Column(name = "arp_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "arp_orden")
	private Integer orden;
	
	@Id
	@Column(name = "arp_id")
	private Long articuloPoliticaId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloPolitica that = (ArticuloPolitica) o;
		return articuloPoliticaId != null && Objects.equals(articuloPoliticaId, that.articuloPoliticaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

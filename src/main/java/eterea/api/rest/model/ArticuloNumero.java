/**
 * Entidad de la tabla articulonumero, sin registros en la BD y sin relaciones
 * REVISAR modelo y uso
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articulonumero", uniqueConstraints = { @UniqueConstraint(columnNames = { "arn_art_id", "arn_serie" , "arn_numero" }) })
@AllArgsConstructor
public class ArticuloNumero extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 966736326600248438L;

	@Column(name = "arn_art_id")
	private String articuloId;
	
	@Column(name = "arn_serie")
	private String serie;
	
	@Column(name = "arn_numero")
	private String numero;
	
	@Column(name = "arn_estado")
	private String estado;
	
	@Column(name = "arn_fecha")
	private String fecha;
	
	@Id
	@Column(name = "arn_id")
	private Long articuloNumeroId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloNumero that = (ArticuloNumero) o;
		return articuloNumeroId != null && Objects.equals(articuloNumeroId, that.articuloNumeroId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

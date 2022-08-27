/**
 * REVISAR: Entidad de la tabla articulosbarras, en esto momentos sin datos en la BD aparentemente seria
 * una relacion entre articulo y su codigo de barra, de ser asi se recomienda cambiar el tipo de
 * de atributoId de String a Articulo asi establecer una relacion One to One o lo que seria mas
 * natural que la entidad Articulo tenga un atributo CodigoDeBarra.-
 */
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
@Table(name = "articulosbarras")
@AllArgsConstructor
public class ArticuloBarra extends Auditable implements Serializable {

 	@Serial
	private static final long serialVersionUID = -4620826410596988827L;

	@Id
	@Column(name = "aba_codigo")
	private String codigo;
	
	@Column(name = "clave")
	private Long articuloBarraId;
	
	@Column(name = "aba_art_id")
	private String articuloId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloBarra that = (ArticuloBarra) o;
		return codigo != null && Objects.equals(codigo, that.codigo);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

/**
 * Entidad de la tabla servicio guarda los registros de los servicios que se ofrecen en el negocio
 * deberia tener una lista de los articulos que incluye, po lo que se plantea una solucion a la clase
 * ArticuloServicio que al estar como atributo simplifica el tratamiento como objeto en el repositorio,
 * servicio y controlador de este objeto
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "servicio")
@AllArgsConstructor
public class Servicio extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 7401219143242258638L;

	@Id
	@Column(name = "ser_id")
	private Integer servicioId;
	
	@Column(name = "ser_corto")
	private String corto;
	
	@Column(name = "ser_detalle")
	private String detalle;

	/*Problable reemplazo  de ArticuloServicio
	@Column(nullable = false)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "articuloservicio",
			joinColumns = @JoinColumn(name = "ars_art_id"), //probablemente se podria cambiar el nombre del campo por articuloID
			inverseJoinColumns = @JoinColumn(name = "ars_ser_id"))//cambiar por servicioID
	private List<Articulo> articulos;*/

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Servicio servicio = (Servicio) o;
		return servicioId != null && Objects.equals(servicioId, servicio.servicioId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

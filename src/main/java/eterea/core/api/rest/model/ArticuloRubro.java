/**
 * Entidad de la tabla articulosrubros
 */
package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
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
@Table(name = "articulosrubros")
@AllArgsConstructor
public class ArticuloRubro extends Auditable implements Serializable {
	@Serial
	private static final long serialVersionUID = -8470827828614569484L;

	@Id
	@Column(name = "codigo")
	private Long articuloRubroId;
	
	@Column(name = "aru_neg_id")
	private Integer negocioId;
	
	private String descripcion;
	
	@Column(name = "unegocio")
	private Integer unidadNegocio;
	
	@Column(name = "aru_restaurant")
	private Byte restaurant;
	
	@Column(name = "aru_prv_id")
	private Long proveedorId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloRubro that = (ArticuloRubro) o;
		return articuloRubroId != null && Objects.equals(articuloRubroId, that.articuloRubroId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

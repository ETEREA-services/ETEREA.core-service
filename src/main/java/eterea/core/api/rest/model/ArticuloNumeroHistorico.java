/**
 * Entidad relacionada a la tabla articulonumeroh sin registros y sin relaciones en la BD
 * REVISAR modelo y uso
 */
package eterea.core.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.api.rest.kotlin.model.Auditable;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articulonumeroh")
@AllArgsConstructor
public class ArticuloNumeroHistorico extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4185254272096941623L;

	@Id
	@Column(name = "anh_id")
	private Long articuloNumeroHistoricoId;
	
	@Column(name = "anh_art_id")
	private String articuloId;
	
	@Column(name = "anh_serie")
	private String serie;
	
	@Column(name = "anh_numero")
	private Long numero;
	
	@Column(name = "anh_estado")
	private String estado;
	
	@Column(name = "anh_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloNumeroHistorico that = (ArticuloNumeroHistorico) o;
		return articuloNumeroHistoricoId != null && Objects.equals(articuloNumeroHistoricoId, that.articuloNumeroHistoricoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

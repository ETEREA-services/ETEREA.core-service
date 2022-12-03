/**
 * Entidad de la tabla articulofecha, en la que se guardan los precios de los articulos segun una fecha
 * dada, para llevar un historico de precios en dolares y pesos; posee una FK a articulosId
 * podria llamarse HistoricoArticuloPrecio
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "articulofecha", uniqueConstraints = { @UniqueConstraint(columnNames = { "articulo_id", "fecha" }) })
@AllArgsConstructor
public class ArticuloFecha extends Auditable implements Serializable {
	@Serial
	private static final long serialVersionUID = 1545964556063591559L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articulofecha_id")
	private Long articulofechaId;

	@Column(name = "articulo_id")
	@Size(max = 20)
	@NotNull
	private String articuloId = "";

	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "precio_usd")
	@NotNull
	private BigDecimal precioUsd = new BigDecimal(0);

	@Column(name = "precio_ars")
	@NotNull
	private BigDecimal precioArs = new BigDecimal(0);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloFecha that = (ArticuloFecha) o;
		return articulofechaId != null && Objects.equals(articulofechaId, that.articulofechaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

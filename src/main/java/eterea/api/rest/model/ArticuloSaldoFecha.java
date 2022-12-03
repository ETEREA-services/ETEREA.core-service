/**
 * Entidad relacionada a la tabla articulossaldofecha
 * REVISAR: ultimo registro 21-02-2013
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
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
//Revisar Nombre Tabla
@Table(name = "articulosaldofecha", uniqueConstraints = { @UniqueConstraint(columnNames = { "asf_cst_id", "asf_art_id", "asf_fecha" }) })
@AllArgsConstructor
public class ArticuloSaldoFecha extends Auditable implements Serializable {
	@Serial
	private static final long serialVersionUID = 8603287140671704061L;

	@Column(name = "asf_cst_id")
	private Integer centroStockId;
	
	@Column(name = "asf_art_id")
	private String articuloId;
	
	@Column(name = "asf_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "asf_saldo")
	private BigDecimal saldo = BigDecimal.ZERO;
	
	@Id
	@Column(name = "clave")
	private Long articuloSaldoFechaId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloSaldoFecha that = (ArticuloSaldoFecha) o;
		return articuloSaldoFechaId != null && Objects.equals(articuloSaldoFechaId, that.articuloSaldoFechaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

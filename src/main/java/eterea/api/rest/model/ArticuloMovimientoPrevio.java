/**
 * Entidad de la tabla articulomovimientoprevio,
 * Relaciones con: FK con clientemovimientoprevio, articulo, detartic y plancta
 * REVISAR: Tabla sin registros
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
@Table(name = "articulomovimientoprevio")
@AllArgsConstructor
public class ArticuloMovimientoPrevio extends Auditable implements Serializable {
	@Serial
	private static final long serialVersionUID = -7042549651914768607L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articulomovimientoprevio_id")
	private Long articuloMovimientoPrevioId;
	
	@Column(name = "clientemovimientoprevio_id")
	private Long clienteMovimientoPrevioId;
	
	private String articuloId;
	private BigDecimal cantidad = BigDecimal.ZERO;
	private BigDecimal precioUnitarioSinIva = BigDecimal.ZERO;
	private BigDecimal precioUnitarioConIva = BigDecimal.ZERO;
	private Long cuentaVenta;
	
	@Column(name = "iva_21")
	private Byte iva21 = 0;
	
	@Column(name = "iva_105")
	private Byte iva105 = 0;
	
	private Byte exento = 0;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaMovimiento;
	
	@Column(name = "articulomovimiento_id")
	private Long articuloMovimientoId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloMovimientoPrevio that = (ArticuloMovimientoPrevio) o;
		return articuloMovimientoPrevioId != null && Objects.equals(articuloMovimientoPrevioId, that.articuloMovimientoPrevioId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

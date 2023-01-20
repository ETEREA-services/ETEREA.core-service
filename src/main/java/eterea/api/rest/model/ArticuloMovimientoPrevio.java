/**
 * Entidad de la tabla articulomovimientoprevio,
 * Relaciones con: FK con clientemovimientoprevio, articulo, detartic y plancta
 * REVISAR: Tabla sin registros
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "articulomovimientoprevio")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArticuloMovimientoPrevio extends Auditable implements Serializable {

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

	@OneToOne(optional = true)
	@JoinColumn(name = "articuloId", insertable = false, updatable = false)
	private Articulo articulo;

	@OneToOne(optional = true)
	@JoinColumn(name = "cuentaVenta", insertable = false, updatable = false)
	private Cuenta cuenta;

}

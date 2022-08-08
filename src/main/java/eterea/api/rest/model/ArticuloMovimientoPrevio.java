/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "articulomovimientoprevio")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloMovimientoPrevio extends Auditable implements Serializable {/**
	 * 
	 */
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
	
}

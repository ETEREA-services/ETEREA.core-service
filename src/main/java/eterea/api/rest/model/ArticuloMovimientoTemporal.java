/**
 * Entidad de la tabla tmdetartic que registra los IP de los movimientos de articulos
 * Nota Personal: se necesita mas análisis para entendimiento
 */
package eterea.api.rest.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "tmdetartic")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ArticuloMovimientoTemporal extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 925755181492758323L;

	@Column(name = "ipaddress")
	private String ipAddress;

	@Column(name = "dat_hwnd")
	private Long hwnd;

	private Long item;
	private Integer item2;

	@Column(name = "cgocentro")
	private Long centroId;

	@Column(name = "cgoarticulo")
	private String articuloId;

	@Column(name = "clavedetartic")
	private Long articuloMovimientoId;

	private String descripcion;

	@Column(name = "preciounitario")
	private BigDecimal precioUnitario = BigDecimal.ZERO;

	private BigDecimal cantidad = BigDecimal.ZERO;
	private BigDecimal total = BigDecimal.ZERO;

	@Column(name = "cgocontable")
	private Long cuenta;

	@Column(name = "preunitciva")
	private BigDecimal precioUnitarioConIva = BigDecimal.ZERO;

	@Column(name = "preunitsiva")
	private BigDecimal precioUnitarioSinIva = BigDecimal.ZERO;

	@Column(name = "preciocompra")
	private BigDecimal precioCompra = BigDecimal.ZERO;

	private Byte iva105;
	private Byte exento;
	private BigDecimal comision = BigDecimal.ZERO;

	@Id
	@Column(name = "clave")
	private Long articuloMovimientoTemporalId;

}

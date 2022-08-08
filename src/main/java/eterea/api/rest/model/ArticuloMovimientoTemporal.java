/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "tmdetartic")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloMovimientoTemporal extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 925755181492758323L;

	@Column(name = "ipaddress")
	private String ipAddress;

	@Column(name = "dat_hwnd")
	private Long hWnd;

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

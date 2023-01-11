/**
 * Entidad vinculada a la tabla detartic, y esta relacionada con la tabla articulomovimientoprevio
 * REVISAR: tabla que posee registros con datos null,
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "detartic")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ArticuloMovimiento extends Auditable implements Serializable {

	private static final long serialVersionUID = -4307496860129226498L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long articuloMovimientoId;

	@Column(name = "clavemovclie")
	private Long clienteMovimientoId;

	@Column(name = "clavemovstock")
	private Long stockMovimientoId;

	@Column(name = "clavemovtenencia")
	private Long tenenciaMovimientoId;

	@Column(name = "cgocentro")
	private Integer centroStockId;

	@Column(name = "cgocomprob")
	private Integer comprobanteId;

	private Integer item = 0;

	@Column(name = "cgoartic")
	private String articuloId;

	@Column(name = "dea_neg_id")
	private Integer negocioId;

	private BigDecimal cantidad = BigDecimal.ZERO;

	@Column(name = "preciounitario")
	private BigDecimal precioUnitario = BigDecimal.ZERO;

	@Column(name = "preunitsiva")
	private BigDecimal precioUnitarioSinIva = BigDecimal.ZERO;

	@Column(name = "preunitciva")
	private BigDecimal precioUnitarioConIva = BigDecimal.ZERO;

	@Column(name = "cgocontable")
	private Long numeroCuenta;

	@Column(name = "iva105")
	private Byte iva105 = 0;

	@Column(name = "exento")
	private Byte exento = 0;

	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaMovimiento;

	@Column(name = "fechafac")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaFactura;

	@Column(name = "dea_nivel")
	private Integer nivel = 0;

	@Column(name = "dea_cic_id")
	private Long cierreCajaId;

	@Column(name = "dea_cir_id")
	private Long cierreRestaurantId;

	@Column(name = "dea_preciocompra")
	private BigDecimal precioCompra = BigDecimal.ZERO;

	@Column(name = "dea_preciovaluacion")
	private BigDecimal precioValuacion = BigDecimal.ZERO;

	@Column(name = "moz_id")
	private Long mozoId;

	@Column(name = "dea_comision")
	private BigDecimal comision = BigDecimal.ZERO;

	@Transient
	private BigDecimal total = BigDecimal.ZERO;

	@OneToOne(optional = true)
	@JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
	private Comprobante comprobante;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "cgoartic", insertable = false, updatable = false)
	private Articulo articulo;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "clavemovclie", insertable = false, updatable = false)
	private ClienteMovimiento clienteMovimiento;
	
}

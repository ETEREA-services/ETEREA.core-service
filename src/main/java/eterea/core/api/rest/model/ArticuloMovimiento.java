/**
 * Entidad vinculada a la tabla detartic, y esta relacionada con la tabla articulomovimientoprevio
 * REVISAR: tabla que posee registros con datos null,
 */
package eterea.core.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "detartic")
@AllArgsConstructor
public class ArticuloMovimiento extends Auditable implements Serializable {
	@Serial
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

	@Column(name = "item")
	@NotNull
	private Integer item = 0;

	@Column(name = "cgoartic")
	private String articuloId;

	@Column(name = "dea_neg_id")
	private Integer negocioId;

	@Column(name = "cantidad")
	@NotNull
	private BigDecimal cantidad = BigDecimal.ZERO;

	@Column(name = "preciounitario")
	@NotNull
	private BigDecimal precioUnitario = BigDecimal.ZERO;

	@Column(name = "preunitsiva")
	@NotNull
	private BigDecimal precioUnitarioSinIva = BigDecimal.ZERO;

	@Column(name = "preunitciva")
	@NotNull
	private BigDecimal precioUnitarioConIva = BigDecimal.ZERO;

	@Column(name = "cgocontable")
	private Long cuenta;

	@Column(name = "iva105")
	@NotNull
	private Byte iva105 = 0;

	@Column(name = "exento")
	@NotNull
	private Byte exento = 0;

	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaMovimiento;

	@Column(name = "fechafac")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaFactura;

	@Column(name = "dea_nivel")
	@NotNull
	private Integer nivel = 0;

	@Column(name = "dea_cic_id")
	private Long cierreCajaId;

	@Column(name = "dea_cir_id")
	private Long cierreRestaurantId;

	@Column(name = "dea_preciocompra")
	@NotNull
	private BigDecimal precioCompra = BigDecimal.ZERO;

	@Column(name = "dea_preciovaluacion")
	@NotNull
	private BigDecimal precioValuacion = BigDecimal.ZERO;

	@Column(name = "moz_id")
	private Long mozoId;

	@Column(name = "dea_comision")
	@NotNull
	private BigDecimal comision = BigDecimal.ZERO;

	@Transient
	@NotNull
	private BigDecimal total = BigDecimal.ZERO;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ArticuloMovimiento that = (ArticuloMovimiento) o;
		return articuloMovimientoId != null && Objects.equals(articuloMovimientoId, that.articuloMovimientoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

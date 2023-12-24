/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "factrenddet", uniqueConstraints = { @UniqueConstraint(columnNames = { "frd_neg_id", "frd_frc_id",
		"frd_prv_id", "frd_cmp_id", "frd_prefijo", "frd_numero" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRendicionDetalle extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6260803567857877923L;

	@Column(name = "frd_neg_id")
	private Integer negocioId;

	@Column(name = "frd_frc_id")
	private Long facturaRendicionCabeceraId;

	@Column(name = "frd_prv_id")
	private Integer proveedorId;

	@Column(name = "frd_cmp_id")
	private Integer comprobanteId;

	@Column(name = "frd_prefijo")
	private Integer prefijo;

	@Column(name = "frd_numero")
	private Long numero;

	@Column(name = "frd_proveedor")
	private String proveedor;

	@Column(name = "frd_comprobante")
	private String comprobante;

	@Column(name = "frd_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "frd_importe")
	private BigDecimal importe = BigDecimal.ZERO;

	@Column(name = "frd_pendiente")
	private Byte pendiente;

	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long facturaRendicionDetalleId;

}

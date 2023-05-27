/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "registrocae", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "rec_tco_id", "rec_prefijo", "rec_nrocomprob" }) })
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Electronico extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7544608234573398384L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rec_id")
	private Long electronicoId;

	@Column(name = "rec_tco_id")
	private Integer comprobanteId;

	@Column(name = "rec_prefijo")
	@NotNull
	private Integer puntoVenta = 0;

	@Column(name = "rec_nrocomprob")
	@NotNull
	private Long numeroComprobante = 0L;

	@Column(name = "rec_cli_id")
	private Long clienteId;

	@Column(name = "rec_cuit")
	private String cuit;

	@Column(name = "rec_total")
	private BigDecimal total = BigDecimal.ZERO;

	@Column(name = "rec_exento")
	private BigDecimal exento = BigDecimal.ZERO;

	@Column(name = "rec_neto")
	private BigDecimal neto21 = BigDecimal.ZERO;

	@Column(name = "rec_neto105")
	private BigDecimal neto105 = BigDecimal.ZERO;

	@Column(name = "rec_iva")
	private BigDecimal iva21 = BigDecimal.ZERO;

	@Column(name = "rec_iva105")
	private BigDecimal iva105 = BigDecimal.ZERO;

	@Column(name = "rec_cae")
	private String cae = "";

	@Column(name = "rec_fecha")
	private String fecha = "";

	@Column(name = "rec_caevenc")
	private String caeVencimiento = "";

	@Column(name = "rec_barras")
	private String codigoBarras = "";

	@Column(name = "tipo_documento")
	private Integer tipoDocumento;

	@Column(name = "numero_documento")
	private BigDecimal numeroDocumento;

	@Column(name = "cliente_movimiento_id_asociado")
	private Long clienteMovimientoIdAsociado;

}

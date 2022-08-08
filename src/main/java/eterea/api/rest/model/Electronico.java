/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private BigDecimal total = BigDecimal.ZERO;

	@Column(name = "rec_exento")
	@NotNull
	private BigDecimal exento = BigDecimal.ZERO;

	@Column(name = "rec_neto")
	@NotNull
	private BigDecimal neto21 = BigDecimal.ZERO;

	@Column(name = "rec_neto105")
	@NotNull
	private BigDecimal neto105 = BigDecimal.ZERO;

	@Column(name = "rec_iva")
	@NotNull
	private BigDecimal iva21 = BigDecimal.ZERO;

	@Column(name = "rec_iva105")
	@NotNull
	private BigDecimal iva105 = BigDecimal.ZERO;

	@Column(name = "rec_cae")
	@NotNull
	private String cae = "";

	@Column(name = "rec_fecha")
	@NotNull
	private String fecha = "";

	@Column(name = "rec_caevenc")
	@NotNull
	private String caeVencimiento = "";

	@Column(name = "rec_barras")
	@NotNull
	private String codigoBarras = "";

	@Column(name = "tipo_documento")
	private Integer tipoDocumento;

	@Column(name = "numero_documento")
	private BigDecimal numeroDocumento;

	@Column(name = "cliente_movimiento_id_asociado")
	private Long clienteMovimientoIdAsociado;

}

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
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "movprov")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorMovimiento extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3800561168186269210L;

	@Column(name = "mpr_emp_id")
	private Integer empresaId;

	@Column(name = "mpr_neg_id")
	private Integer negocioId;

	@Column(name = "cgoprov")
	private Long proveedorId;

	@Column(name = "cgocomprob")
	private Integer comprobanteId;

	@Column(name = "fechacomprob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaComprobante;

	@Column(name = "mpr_fechavenc")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;

	private Integer prefijo;

	@Column(name = "nrocomprob")
	private Long numeroComprobante;

	private BigDecimal importe = BigDecimal.ZERO;
	private BigDecimal cancelado = BigDecimal.ZERO;
	private BigDecimal aplicado = BigDecimal.ZERO;
	private BigDecimal neto = BigDecimal.ZERO;

	@Column(name = "netocancelado")
	private BigDecimal netoCancelado = BigDecimal.ZERO;

	@Column(name = "montoiva")
	private BigDecimal montoIva = BigDecimal.ZERO;

	@Column(name = "montoiva27")
	private BigDecimal montoIva27 = BigDecimal.ZERO;

	@Column(name = "montoiva105")
	private BigDecimal montoIva105 = BigDecimal.ZERO;

	@Column(name = "perciva")
	private BigDecimal percepcionIva = BigDecimal.ZERO;

	@Column(name = "percingbrutos")
	private BigDecimal percepcionIngresosBrutos = BigDecimal.ZERO;

	@Column(name = "gng")
	private BigDecimal gastosNoGravados = BigDecimal.ZERO;

	private BigDecimal ajustes = BigDecimal.ZERO;

	@Column(name = "fechareg")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;

	@Column(name = "nrocompconta")
	private Long ordenContable;

	@Column(name = "montofactc")
	private BigDecimal montoFacturaC = BigDecimal.ZERO;

	@Column(name = "montosujetoretib")
	private BigDecimal montoSujetoRetencionesIngresosBrutos = BigDecimal.ZERO;

	@Column(name = "cgoretib")
	private Long codigoRetencionIngresosBrutos;

	@Column(name = "nrocompretib")
	private Long numeroRetencionIngresosBrutos;

	private String concepto;

	@Column(name = "mpr_cic_id")
	private Long cierreCajaId;

	@Column(name = "mpr_nivel")
	private Integer nivel;

	@Column(name = "mpr_neg_id_paga")
	private Integer negocioIdPagador;

	@Column(name = "mpr_concursada")
	private Byte concursada;

	@Column(name = "mpr_importeconcursado")
	private BigDecimal importeConcursado = BigDecimal.ZERO;

	@Column(name = "mpr_fechaconcursado")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContableConcursado;

	@Column(name = "mpr_nrocompconcursado")
	private Integer ordenContableConcursado;

	@Column(name = "mpr_marca")
	private Byte marca;

	@Column(name = "mpr_orden")
	private Integer orden;

	@Column(name = "mpr_transferida")
	private Byte transferida;

	@Id
	@Column(name = "clave")
	private Long proveedorMovimientoId;

}

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "movclie")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteMovimiento extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1810250202563003951L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long clienteMovimientoId;

	@Column(name = "cgocomprob")
	private Integer comprobanteId;

	@Column(name = "prefijo")
	@NotNull
	private Integer puntoVenta = 0;

	@Column(name = "nrocomprob")
	@NotNull
	private Long numeroComprobante = 0L;

	@Column(name = "fechacomprob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaComprobante;

	@Column(name = "cgoclie")
	private Long clienteId;

	@Column(name = "mcl_fechavenc")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;

	@Column(name = "mcl_neg_id")
	private Integer negocioId;

	@Column(name = "mcl_emp_id")
	private Integer empresaId;

	@Column(name = "importe")
	@NotNull
	private BigDecimal importe = BigDecimal.ZERO;

	@Column(name = "cancelado")
	@NotNull
	private BigDecimal cancelado = BigDecimal.ZERO;

	@Column(name = "neto")
	@NotNull
	private BigDecimal neto = BigDecimal.ZERO;

	@Column(name = "netocancelado")
	@NotNull
	private BigDecimal netoCancelado = BigDecimal.ZERO;

	@Column(name = "montoiva")
	@NotNull
	private BigDecimal montoIva = BigDecimal.ZERO;

	@Column(name = "montoivarni")
	@NotNull
	private BigDecimal montoIvaRni = BigDecimal.ZERO;

	@Column(name = "reintegroturista")
	@NotNull
	private BigDecimal reintegroTurista = BigDecimal.ZERO;

	@Column(name = "fechareg")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;

	@Column(name = "nrocompconta")
	private Integer ordenContable;

	@Column(name = "recibo")
	@NotNull
	private Byte recibo = 0;

	@Column(name = "mcl_asignado")
	@NotNull
	private Byte asignado = 0;

	@Column(name = "anulada")
	@NotNull
	private Byte anulada = 0;

	@Column(name = "decreto104316")
	@NotNull
	private Byte decreto104316 = 0;

	@Column(name = "tipocompro")
	private String letraComprobante;

	@Column(name = "montoexento")
	@NotNull
	private BigDecimal montoExento = BigDecimal.ZERO;

	@Column(name = "nroreserva")
	private Long reservaId;

	@Column(name = "ctacte")
	@NotNull
	private BigDecimal montoCuentaCorriente = BigDecimal.ZERO;

	@Column(name = "mcl_cic_id")
	private Long cierreCajaId;

	@Column(name = "mcl_cir_id")
	private Long cierreRestaurantId;

	@Column(name = "mcl_nivel")
	@NotNull
	private Integer nivel = 0;

	@Column(name = "mcl_eliminar")
	@NotNull
	private Byte eliminar = 0;

	@Column(name = "mcl_ctacte")
	@NotNull
	private Byte cuentaCorriente = 0;

	@Column(name = "mcl_letras")
	@NotNull
	private String letras = "";

	@Column(name = "mcl_cae")
	@NotNull
	private String cae = "";

	@Column(name = "mcl_caevenc")
	@NotNull
	private String caeVencimiento = "";

	@Column(name = "mcl_barras")
	@NotNull
	private String codigoBarras = "";

	@Column(name = "mcl_particip")
	@NotNull
	private BigDecimal participacion = BigDecimal.ZERO;

	@Column(name = "mcl_mon_id")
	private Integer monedaId;

	@Column(name = "mcl_cotiz")
	@NotNull
	private BigDecimal cotizacion = BigDecimal.ZERO;
	
	private String observaciones;

	@Column(name = "clavev")
	private Long clienteMovimientoIdSlave;

}

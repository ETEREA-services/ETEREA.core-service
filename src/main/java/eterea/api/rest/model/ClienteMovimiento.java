/**
 * Entidad de la tabla movclie registra los movimientos de un cliente,
 * Se deberia replantear el modelo ya que presenta duplicidad de informacion en la DB
 * que se podria obtener mediante relaciones, actualmente se encuentra relacionado solo con
 * clientemovimientoprevio
 */
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "movclie")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ClienteMovimiento extends Auditable implements Serializable {

	private static final long serialVersionUID = 1810250202563003951L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long clienteMovimientoId;

	@Column(name = "cgocomprob")
	private Integer comprobanteId;

	@Column(name = "prefijo")
	private Integer puntoVenta = 0;

	@Column(name = "nrocomprob")
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

	private BigDecimal importe = BigDecimal.ZERO;
	private BigDecimal cancelado = BigDecimal.ZERO;
	private BigDecimal neto = BigDecimal.ZERO;

	@Column(name = "netocancelado")
	private BigDecimal netoCancelado = BigDecimal.ZERO;

	@Column(name = "montoiva")
	private BigDecimal montoIva = BigDecimal.ZERO;

	@Column(name = "montoivarni")
	private BigDecimal montoIvaRni = BigDecimal.ZERO;

	@Column(name = "reintegroturista")
	private BigDecimal reintegroTurista = BigDecimal.ZERO;

	@Column(name = "fechareg")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;

	@Column(name = "nrocompconta")
	private Integer ordenContable;

	private Byte recibo = 0;

	@Column(name = "mcl_asignado")
	private Byte asignado = 0;

	private Byte anulada = 0;
	private Byte decreto104316 = 0;

	@Column(name = "tipocompro")
	private String letraComprobante;

	@Column(name = "montoexento")
	private BigDecimal montoExento = BigDecimal.ZERO;

	@Column(name = "nroreserva")
	private Long reservaId;

	@Column(name = "ctacte")
	private BigDecimal montoCuentaCorriente = BigDecimal.ZERO;

	@Column(name = "mcl_cic_id")
	private Long cierreCajaId;

	@Column(name = "mcl_cir_id")
	private Long cierreRestaurantId;

	@Column(name = "mcl_nivel")
	private Integer nivel = 0;

	@Column(name = "mcl_eliminar")
	private Byte eliminar = 0;

	@Column(name = "mcl_ctacte")
	private Byte cuentaCorriente = 0;

	@Column(name = "mcl_letras")
	private String letras = "";

	@Column(name = "mcl_cae")
	private String cae = "";

	@Column(name = "mcl_caevenc")
	private String caeVencimiento = "";

	@Column(name = "mcl_barras")
	private String codigoBarras = "";

	@Column(name = "mcl_particip")
	private BigDecimal participacion = BigDecimal.ZERO;

	@Column(name = "mcl_mon_id")
	private Integer monedaId;

	@Column(name = "mcl_cotiz")
	private BigDecimal cotizacion = BigDecimal.ZERO;
	
	private String observaciones;

	@Column(name = "clavev")
	private Long clienteMovimientoIdSlave;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
	private Comprobante comprobante;

	@OneToOne(optional = true)
	@JoinColumn(name = "cgoclie", insertable = false, updatable = false)
	private Cliente cliente;

}

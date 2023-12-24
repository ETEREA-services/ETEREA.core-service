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
@Table(name = "movhabitaciones")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionMovimiento extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3161629242243280949L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long habitacionMovimientoId;

	@Column(name = "cgocliente")
	private Long clienteId;

	@Column(name = "cgocomprob")
	private Long comprobanteId;

	@Column(name = "nrocomprobante")
	private Long numeroComprobante = 0L;

	@Column(name = "fechaingreso")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaIngreso;

	@Column(name = "fechasalida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaSalida;

	@Column(name = "cgotarifa")
	private Long tarifaId;

	@Column(name = "conceptotarifa")
	private String conceptoTarifa = "";

	@Column(name = "preciounitariotarifa")
	private BigDecimal precioUnitarioTarifa = BigDecimal.ZERO;

	@Column(name = "cgohabitacion")
	private Long habitacionId;

	@Column(name = "cantidadpax")
	private Long cantidadPax = 0L;

	@Column(name = "diasfacturados")
	private Long diasFacturados = 0L;

	@Column(name = "importefacturado")
	private BigDecimal importeFacturado = BigDecimal.ZERO;

	@Column(name = "cgoestadores")
	private Long estadoReservaId;

	@Column(name = "fechaoperacion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaOperacion;

	@Column(name = "fechavto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;

	@Column(name = "nroreserva")
	private Long numeroReserva = 0L;

	private Long item = 0L;

	@Column(name = "tarifastandard")
	private Byte tarifaStandard = (byte) 0;

	@Column(name = "mha_paxmay")
	private Integer cantidadPaxMayor = 0;

	@Column(name = "mha_paxmen")
	private Integer cantidadPaxMenor = 0;

	@Column(name = "mha_observ")
	private String observaciones = "";

}

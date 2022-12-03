/**
 * 
 */
package eterea.api.rest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "vw_movhabdatos")
@Immutable
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionMovimientoExtended implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3834618433034959519L;

	@Id
	@Column(name = "clave")
	private Long habitacionMovimientoId;

	@Column(name = "cgocliente")
	@NotNull
	private Long clienteId = 0L;

	@Column(name = "cgocomprob")
	private Long comprobanteId;

	@Column(name = "nrocomprobante")
	@NotNull
	private Long numeroComprobante = 0L;

	@Column(name = "fechaingreso")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaIngreso;

	@Column(name = "fechasalida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaSalida;

	@Column(name = "cgotarifa")
	@NotNull
	private Long tarifaId = 0L;

	@Column(name = "conceptotarifa")
	@NotNull
	private String conceptoTarifa = "";

	@Column(name = "preciounitariotarifa")
	@NotNull
	private BigDecimal precioUnitarioTarifa = BigDecimal.ZERO;

	@Column(name = "cgohabitacion")
	@NotNull
	private Long habitacionId = 0L;

	@Column(name = "cantidadpax")
	@NotNull
	private Long cantidadPax = 0L;

	@Column(name = "diasfacturados")
	@NotNull
	private Long diasFacturados = 0L;

	@Column(name = "importefacturado")
	@NotNull
	private BigDecimal importeFacturado = BigDecimal.ZERO;

	@Column(name = "cgoestadores")
	@NotNull
	private Long estadoReservaId = 0L;

	@Column(name = "fechaoperacion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaOperacion;

	@Column(name = "fechavto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;

	@Column(name = "nroreserva")
	@NotNull
	private Long numeroReserva = 0L;

	@Column(name = "item")
	@NotNull
	private Long item = 0L;

	@Column(name = "tarifastandard")
	@NotNull
	private Byte tarifaStandard = (byte) 0;

	@Column(name = "mha_paxmay")
	@NotNull
	private Integer cantidadPaxMayor = 0;

	@Column(name = "mha_paxmen")
	@NotNull
	private Integer cantidadPaxMenor = 0;

	@Column(name = "mha_observ")
	@NotNull
	private String observaciones = "";
	
	@Column(name = "nombrecliente")
	private String nombreCliente;
	
	@Column(name = "tel")
	private String telefono;

	@Column(name = "email")
	private String email;

	@Column(name = "tipocgoestadores")
	private String estadoReservaTipo;

	@Column(name = "descripcioncgoestadores")
	private String estadoReservaDescripcion;

	@Column(name = "tipohabitacion")
	private String tipoHabitacion;

	@Column(name = "descripciontarifa")
	private String descripcionTarifa;

}

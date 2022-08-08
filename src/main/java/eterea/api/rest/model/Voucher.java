/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "voucher")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Voucher extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2864026913751798775L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vou_id")
	private Long voucherId;

	@Column(name = "vou_fechareserva")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaToma;

	@Column(name = "vou_fechain")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaServicio;

	@Column(name = "vou_vto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;

	@Column(name = "vou_horavto")
	private Time horaVencimiento;

	@Column(name = "vou_nombrepax")
	private String nombrePax;

	@Column(name = "vou_paxs")
	private Integer paxs;

	@Column(name = "vou_subeen")
	private String subeEn;

	@Column(name = "vou_productos")
	private String productos;

	@Column(name = "vou_voucher")
	private Byte tienenVoucher;

	@Column(name = "vou_cli_id")
	private Long clienteId;

	@Column(name = "vou_observ")
	private String observaciones;

	@Column(name = "vou_confirmado")
	private Byte confirmado;

	private Byte pagaCacheuta;

	@Column(name = "vou_hot_id")
	private Long hotelId;

	@Column(name = "vou_cliente")
	private String contacto;

	@Column(name = "vou_paxsreales")
	private Integer paxsReales;

	@Column(name = "vou_pro_id")
	private Integer proveedorId;

	@Column(name = "vou_planilla")
	private String planilla;

	@Column(name = "vou_res_id")
	private Long reservaId;

	@Column(name = "vou_nrovoucher")
	private String numeroVoucher;

	@Column(name = "vou_usuario", insertable = false, updatable = false)
	private String usuario;

	@Column(name = "vou_fecharecepcion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaRecepcion;

	@Column(name = "vou_fechaemision")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaEmision;

	@Column(name = "vou_numero")
	private String numero;

	@Column(name = "vou_cantidadpax")
	private Integer cantidadPax;

	@Column(name = "vou_nombre")
	private String nombre;

	@Column(name = "vou_contraslado")
	private Byte conTraslado;

	@Column(name = "vou_paxsnoshow")
	private Integer paxsNoShow;

	@Column(name = "vou_reo_id")
	private Integer reservaOrigenId;

	private Byte fechaAbierta;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "vou_usuario")
	@JsonIgnore
	private Usuario user;

}

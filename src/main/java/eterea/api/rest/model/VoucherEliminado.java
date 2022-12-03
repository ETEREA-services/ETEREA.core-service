/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.OffsetDateTime;

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
@Table(name = "voucherelim")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEliminado extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 985641529143454195L;

	@Id
	@Column(name = "vou_id")
	private Long voucherId;
	
	@Column(name = "vou_fechareserva")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaReserva;
	
	@Column(name = "vou_fechain")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaInicio;
	
	@Column(name = "vou_vto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime vencimiento;
	
	@Column(name = "vou_horavto")
	private Time horaVencimiento;
	
	@Column(name = "vou_nombrepax")
	private String nombrePax;
	
	@Column(name = "vous_paxs")
	private Integer paxs;
	
	@Column(name = "vou_subeen")
	private String subeEn;
	
	@Column(name = "vou_productos")
	private String productos;
	
	@Column(name = "vou_voucher")
	private Byte voucher;
	
	@Column(name = "vou_cli_id")
	private Long clienteId;
	
	@Column(name = "vou_observ")
	private String observaciones;
	
	@Column(name = "vou_confirmado")
	private Byte confirmado;
	
	@Column(name = "paga_cacheuta")
	private Byte pagaCacheuta;
	
	@Column(name = "vou_hot_id")
	private Long hotelId;
	
	@Column(name = "vou_cliente")
	private String cliente;
	
	@Column(name = "vou_paxsreales")
	private Integer paxsReales;
	
	@Column(name = "vou_pro_id")
	private Integer proveedorId;
	
	@Column(name = "vou_planilla")
	private String planilla;
	
	@Column(name = "vou_res_id")
	private Long restaurantId;
	
	@Column(name = "vou_nrovoucher")
	private String numeroVoucher;
	
	@Column(name = "vou_usuario")
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
	
	@Column(name = "fecha_abierta")
	private Byte fechaAbierta;
}

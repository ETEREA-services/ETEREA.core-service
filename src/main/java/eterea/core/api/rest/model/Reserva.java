/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author daniel
 *
 */
@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reserva extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4962064245710790458L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id")
	private Long reservaId;

	@Column(name = "res_neg_id")
	private Integer negocioId;

	@Column(name = "res_cli_id")
	private Long clienteId;

	@Column(name = "res_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaToma;

	@Column(name = "res_in")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaInServicio;

	@Column(name = "res_out")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaOutServicio;

	@Column(name = "res_vto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;

	@Column(name = "res_horavto")
	@Temporal(TemporalType.TIME)
	private Date horaVencimiento;

	@Column(name = "res_avisomail")
	private Byte avisoMail = 0;

	@Column(name = "res_pendiente")
	private Byte pendiente = 0;

	@Column(name = "res_confirmada")
	private Byte confirmada = 0;

	@Column(name = "res_facturada")
	private Byte facturada = 0;

	@Column(name = "res_anulada")
	private Byte anulada = 0;

	@Column(name = "res_eliminada")
	private Byte eliminada = 0;

	private Byte verificada = 0;

	@Column(name = "res_nombrepax")
	private String nombrePax = "";

	@Column(name = "res_paxs")
	private Integer cantidadPaxs = 0;

	@Column(name = "res_observaciones")
	private String observaciones = "";

	@Column(name = "res_vou_id")
	private Long voucherId;

	@Column(name = "res_pagacomision")
	private Byte pagaComision = 0;

	@Column(name = "res_obscomision")
	private String observacionesComision = "";

	@Column(name = "res_comisionpagada")
	private Byte comisionPagada = 0;

	@Column(name = "res_pagacacheuta")
	private Byte pagaCacheuta = 0;

	@Column(name = "res_facturadofuera")
	private Byte facturadoFuera = 0;

	@Column(name = "res_reservaarticulo")
	private String reservaArticulos = "";

	@Column(name = "res_usuario")
	private String usuario = "";

	@Column(name = "res_cliente")
	private String contacto = "";

	@Column(name = "res_reo_ID")
	private Integer reservaOrigenId;

	@Column(name = "facturarextranjero")
	private Byte facturarExtranjero = 0;

	@Column(name = "fecha_abierta")
	private Byte fechaAbierta = 0;

	@OneToOne
	@JoinColumn(name = "res_cli_id", insertable = false, updatable = false)
	private Cliente cliente;

}

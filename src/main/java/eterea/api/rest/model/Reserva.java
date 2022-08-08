/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name = "reserva")
@EqualsAndHashCode(callSuper = false)
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
	private Date fechatoma;

	@Column(name = "res_in")
	private Date fechainservicio;

	@Column(name = "res_out")
	private Date fechaoutservicio;

	@Column(name = "res_vto")
	private Date fechavencimiento;

	@Column(name = "res_horavto")
	@Temporal(TemporalType.TIME)
	private Date horavencimiento;

	@Column(name = "res_avisomail")
	@NotNull
	private Byte avisomail = 0;

	@Column(name = "res_pendiente")
	@NotNull
	private Byte pendiente = 0;

	@Column(name = "res_confirmada")
	@NotNull
	private Byte confirmada = 0;

	@Column(name = "res_facturada")
	@NotNull
	private Byte facturada = 0;

	@Column(name = "res_anulada")
	@NotNull
	private Byte anulada = 0;

	@Column(name = "res_eliminada")
	@NotNull
	private Byte eliminada = 0;

	@Column(name = "verificada")
	@NotNull
	private Byte verificada = 0;

	@Column(name = "res_nombrepax")
	@NotNull
	@Size(max = 250)
	private String nombrepax = "";

	@Column(name = "res_paxs")
	@NotNull
	private Integer cantidadpaxs = 0;

	@Column(name = "res_observaciones")
	@NotNull
	@Size(max = 250)
	private String observaciones = "";

	@Column(name = "res_vou_id")
	@NotNull
	private Long voucherId = 0L;

	@Column(name = "res_pagacomision")
	@NotNull
	private Byte pagacomision = 0;

	@Column(name = "res_obscomision")
	@NotNull
	@Size(max = 250)
	private String observcomision = "";

	@Column(name = "res_comisionpagada")
	@NotNull
	private Byte comisionpagada = 0;

	@Column(name = "res_pagacacheuta")
	@NotNull
	private Byte pagacacheuta = 0;

	@Column(name = "res_facturadofuera")
	@NotNull
	private Byte facturadofuera = 0;

	@Column(name = "res_reservaarticulo")
	@NotNull
	@Size(max = 255)
	private String reservaarticulos = "";

	@Column(name = "res_usuario")
	@NotNull
	@Size(max = 20)
	private String usuario = "";

	@Column(name = "res_cliente")
	@NotNull
	@Size(max = 50)
	private String contacto = "";

	@Column(name = "res_reo_ID")
	@NotNull
	private Integer reservaorigenId = 0;

	@Column(name = "facturarextranjero")
	@NotNull
	private Byte facturarextranjero = 0;

	@Column(name = "fecha_abierta")
	@NotNull
	private Byte fechaabierta = 0;

}

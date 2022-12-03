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
@Table(name = "reservahist")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaHistorico extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1291637871725100741L;

	@Id
	@Column(name = "clave")
	private Long reservaHistoricoId;
	
	@Column(name = "res_id")
	private Long reservaId;
	
	@Column(name = "res_cuando")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime cuando;
	
	@Column(name = "res_neg_id")
	private Integer negocioId;
	
	@Column(name = "res_cli_id")
	private Long clienteId;
	
	@Column(name = "res_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "res_in")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime in;
	
	@Column(name = "res_out")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime out;
	
	@Column(name = "res_vto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime vencimiento;
	
	@Column(name = "res_horavto")
	private Time horaVencimiento;
	
	@Column(name = "res_avisomail")
	private Byte avisoMail;
	
	@Column(name = "res_pendiente")
	private Byte pendiente;
	
	@Column(name = "res_confirmada")
	private Byte confirmada;
	
	@Column(name = "res_facturada")
	private Byte facturada;
	
	@Column(name = "res_anulada")
	private Byte anulada;
	
	@Column(name = "res_eliminada")
	private Byte eliminada;
	
	@Column(name = "res_nombrepax")
	private String nombrePax;
	
	@Column(name = "res_paxs")
	private Integer paxs;
	
	@Column(name = "res_observaciones")
	private String observaciones;
	
	@Column(name = "res_vou_id")
	private Long voucherId;
	
	@Column(name = "res_pagacomision")
	private Byte pagaComision;
	
	@Column(name = "res_obscomision")
	private String observacionComision;
	
	@Column(name = "res_comisionpagada")
	private Byte comisionPagada;
	
	@Column(name = "res_pagacacheuta")
	private Byte pagaCacheuta;
	
	@Column(name = "res_facturadofuera")
	private Byte facturadoFuera;
	
	@Column(name = "res_reservaarticulo")
	private String reservaArticulo;
	
	@Column(name = "res_usuario")
	private String usuario;
	
	@Column(name = "res_reo_id")
	private Integer reservaOrigenId;
	
	@Column(name = "fecha_abierta")
	private Byte fechaAbierta;
}

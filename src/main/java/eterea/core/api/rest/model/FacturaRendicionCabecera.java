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
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "factrencab", uniqueConstraints = { @UniqueConstraint(columnNames = { "frc_neg_id", "frc_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRendicionCabecera extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4300875701288532104L;

	@Column(name = "frc_neg_id")
	private Integer negocioId;
	
	@Column(name = "frc_id")
	private Long facturaRendicionId;
	
	@Column(name = "frc_cmp_id")
	private Integer comprobanteId;
	
	@Column(name = "frc_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "frc_total")
	private BigDecimal total = BigDecimal.ZERO;
	
	@Column(name = "frc_pagada")
	private Byte pagada;
	
	@Column(name = "frc_tranferida")
	private Byte tranferida;
	
	@Column(name = "frc_cuentamaestro")
	private BigDecimal cuentaMestro = BigDecimal.ZERO;
	
	@Column(name = "frc_fechacontable")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;
	
	@Column(name = "frc_nrocompcontable")
	private Integer ordenContable;
	
	@Column(name = "frc_neg_id_paga")
	private Integer negocioIdPagada;
	
	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long facturaRendicionCabeceraId;
	
}

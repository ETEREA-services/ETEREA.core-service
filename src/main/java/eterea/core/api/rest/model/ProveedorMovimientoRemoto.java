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
@Table(name = "movprovremoto", uniqueConstraints = { @UniqueConstraint(columnNames = { "mre_neg_id", "cgoprov", "cgocomprob", "prefijo", "nrocomprob", "mre_orden" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorMovimientoRemoto extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6437583707738258903L;

	@Column(name = "mre_neg_id")
	private Integer negocioId;
	
	@Column(name = "cgoprov")
	private Long proveedorId;
	
	@Column(name = "cgocomprob")
	private Integer comprobanteId;
	
	private Integer prefijo;
	
	@Column(name = "nrocomprob")
	private Long numeroComprobante;
	
	@Column(name = "mre_orden")
	private Integer orden; 
	
	@Column(name = "fechacomprob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaComprobante;
	
	private BigDecimal importe = BigDecimal.ZERO;
	private BigDecimal neto = BigDecimal.ZERO; 
	
	@Column(name = "mre_emp_id")
	private Integer empresaId;
	
	@Column(name = "mre_nombreprov")
	private String nombreProveedor;
	
	@Column(name = "mre_nombrecomp")
	private String nombreComprobante;
	
	@Column(name = "fechareg")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaRegistro;
	
	@Column(name = "nrocompconta")
	private Long ordenContable;
	
	@Column(name = "mre_pagada")
	private Byte pagada;
	
	@Column(name = "mre_cuentamestro")
	private BigDecimal cuentaMaestro = BigDecimal.ZERO;
	
	@Column(name = "mre_cuit")
	private String cuit;

	@Id
	@Column(name = "clave")
	private Long proveedorMovimientoRemotoId;

}

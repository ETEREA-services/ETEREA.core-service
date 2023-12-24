/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


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
@Table(name = "registrocae", uniqueConstraints = { @UniqueConstraint(columnNames = { "rec_tco_id", "rec_prefijo", "rec_nrocomprob" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCae extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1382007434237994705L;

	@Id
	@Column(name = "rec_id")
	private Long registroCaeId;
	
	@Column(name = "rec_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "rec_prefijo")
	private Integer prefijo;
	
	@Column(name = "rec_nrocomprob")
	private Long numeroComprobante;
	
	@Column(name = "rec_cli_id")
	private Long clienteId;
	
	@Column(name = "rec_cuit")
	private String cuit;
	
	@Column(name = "rec_total")
	private BigDecimal total = BigDecimal.ZERO;
	
	@Column(name = "rec_exento")
	private BigDecimal exento = BigDecimal.ZERO;
	
	@Column(name = "rec_neto")
	private BigDecimal neto = BigDecimal.ZERO;
	
	@Column(name = "rec_neto105")
	private BigDecimal neto105 = BigDecimal.ZERO;
	
	@Column(name = "rec_iva")
	private BigDecimal iva = BigDecimal.ZERO;
	
	@Column(name = "rec_iva105")
	private BigDecimal iva105 = BigDecimal.ZERO;
	
	@Column(name = "rec_cae")
	private String cae;
	
	@Column(name = "rec_fecha")
	private String fecha;
	
	@Column(name = "rec_caevenc")
	private String caeVencimiento;
	
	@Column(name = "rec_barras")
	private String barras;
	
	@Column(name = "tipo_documento")
	private Integer tipoDocumento;
	
	@Column(name = "numero_documento")
	private BigDecimal numeroDocumento = BigDecimal.ZERO;
	
	@Column(name = "cliente_movimiento_id_asociado")
	private Long clienteMovimientoIdAsociado;
}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "valores")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ValorMovimiento extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3726603674449553175L;

	@Id
	@Column(name = "clave")
	private Long valorMovimientoId;
	
	@Column(name = "val_neg_id")
	private Integer negocioId;
	
	@Column(name = "codigo")
	private Integer valorId;
	
	@Column(name = "cgoprov")
	private Long proveedorId;
	
	@Column(name = "cgocli")
	private Long clienteId;
	
	@Column(name = "fechaemi")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaEmision;
	
	@Column(name = "fechavto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;
	
	@Column(name = "val_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "nrocomprob")
	private Long numeroComprobante;
	
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "cgocontable")
	private Long cuenta;
	
	@Column(name = "fechareg")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;
	
	@Column(name = "nrocompcontable")
	private Long ordenContable;
	
	@Column(name = "clavemovp")
	private Long proveedorMovimientoId;
	
	@Column(name = "clavemovv")
	private Long clienteMovimientoId;
	
	private String titular;
	private String banco;
	private String receptor;
	
	@Column(name = "cgoestado")
	private Integer estadoId;
	
	@Column(name = "fechaentrega")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaEntrega;
	
	private Long tanda;
	
	@Column(name = "tandaindex")
	private Long tandaIndex;
	
	@Column(name = "val_cic_id")
	private Long cierreCajaId;
	
	@Column(name = "val_nivel")
	private Integer nivel;
	
	private String observaciones;
	
}

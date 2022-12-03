/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "valorestemp")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ValorMovimientoTemporal extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -399298751239525620L;

	@Id
	@Column(name = "clave")
	private Long valorMovimientoTemporalId;
	
	@Column(name = "val_neg_id")
	private Integer negocioId;
	
	@Column(name = "codigo")
	private Integer valorId;;
	
	@Column(name = "cgoprov")
	private Long proveedorId;
	
	@Column(name = "cgocli")
	private Long clienteId;
	
	private Integer comprobanteId;
	
	@Column(name = "fechaemi")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaEmision;
	
	@Column(name = "fechavto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaVencimiento;
	
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
	
	@Column(name = "val_cic_id")
	private Long cierreCajaId;
	
	@Column(name = "val_nivel")
	private Integer nivel;

}

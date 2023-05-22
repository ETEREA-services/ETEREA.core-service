/**
 * 
 */
package eterea.core.api.rest.model;

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
@Table(name = "movbancos")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BancoMovimiento extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 9130806392439402847L;

	@Id
	@Column(name = "mba_id")
	private Long bancoMovimientoId;
	
	@Column(name = "mba_ban_id")
	private Integer bancoId;
	
	@Column(name = "mba_cuenta")
	private String cuenta;
	
	@Column(name = "mba_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mba_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "mba_comprobante")
	private Long comprobante;
	
	@Column(name = "mba_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "mba_observaciones")
	private String observaciones;
	
	@Column(name = "mba_fechacontable")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;
	
	@Column(name = "mba_nrocompcontable")
	private Integer ordenContable;
}

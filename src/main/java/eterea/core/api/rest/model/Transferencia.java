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
@Table(name = "transferencias", uniqueConstraints = { @UniqueConstraint(columnNames = { "tra_dneg_id", "tra_hneg_id", "tra_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4287866826500014851L;

	@Column(name = "tra_dneg_id")
	private Integer negocioIdDesde;
	
	@Column(name = "tra_hneg_id")
	private Integer negocioIdHasta;
	
	@Column(name = "tra_id")
	private Long tranferenciaId;
	
	@Column(name = "tra_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "tra_total")
	private BigDecimal total = BigDecimal.ZERO;
	
	@Column(name = "tra_nrocontable")
	private Integer ordenContable;
	
	@Column(name = "tra_transferido")
	private Byte transferido;
	
	@Column(name = "tra_cmp_id")
	private Integer comprobanteId;
	
	@Column(name = "tra_fechaotro")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaOtro;
	
	@Id
	private Long clave;
}

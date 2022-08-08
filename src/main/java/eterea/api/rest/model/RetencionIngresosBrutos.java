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
@Table(name = "retencionib")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RetencionIngresosBrutos extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8329849664304175811L;

	@Column(name = "valores_id")
	private Long valorMovimientoId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	private BigDecimal neto = BigDecimal.ZERO;
	private Integer contribuyenteId;
	private Integer categoriaId;
	private Integer posicionId;
	private BigDecimal base = BigDecimal.ZERO;
	private BigDecimal alicuota = BigDecimal.ZERO;
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Id
	private String autoId;
	
	private String userId;
}

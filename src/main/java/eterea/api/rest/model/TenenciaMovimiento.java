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
@Table(name = "movtenencia")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TenenciaMovimiento extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1840132721651799639L;

	@Id
	@Column(name = "clave")
	private Long tenenciaMovimientoId;
	
	@Column(name = "mot_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "mot_orden")
	private Long orden;
	
	@Column(name = "mot_cgocentro")
	private Integer centroStockId;
	
	@Column(name = "mot_cgoarticulo")
	private String articuloId;
	
	@Column(name = "mot_indice")
	private BigDecimal indice = BigDecimal.ZERO;
	
	@Column(name = "mot_cotizacion")
	private BigDecimal cotizacion = BigDecimal.ZERO;
	
	@Column(name = "mot_fechacontable")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;
	
	@Column(name = "mot_nrocompcontable")
	private Integer ordenContable;
}

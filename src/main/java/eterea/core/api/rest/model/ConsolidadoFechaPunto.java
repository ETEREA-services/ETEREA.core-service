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
@Table(name = "consfechapunto", uniqueConstraints = { @UniqueConstraint(columnNames = { "negocio_id", "punto_venta", "fecha", "cuenta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidadoFechaPunto extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5602707481136834636L;

	@Id
	@Column(name = "negocio_id")
	private Integer negocioId;
	
	@Column(name = "punto_venta")
	private Integer puntoVenta;
	
	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "cuenta")
	private Long cuenta;
	
	@Column(name = "deudor")
	private BigDecimal deudor = BigDecimal.ZERO;
	
	@Column(name = "acreedor")
	private BigDecimal acreedor = BigDecimal.ZERO;
	
}

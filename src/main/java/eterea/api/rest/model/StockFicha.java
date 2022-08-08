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
import javax.persistence.UniqueConstraint;

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
@Table(name = "stockficha", uniqueConstraints = { @UniqueConstraint(columnNames = { "stf_cst_id", "stf_art_id", "stf_item" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StockFicha extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 979918536412632381L;

	@Column(name = "stf_cst_id")
	private Integer centroStockId;
	
	@Column(name = "stf_art_id")
	private String articuloId;
	
	@Column(name = "stf_item")
	private Integer item = 0;
	
	@Column(name = "stf_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "stf_preciocompra")
	private BigDecimal precioCompra = BigDecimal.ZERO;
	
	@Column(name = "stf_saldo")
	private BigDecimal saldo = BigDecimal.ZERO;
	
	@Id
	@Column(name = "stf_id")
	private Long stockFichaId;
}

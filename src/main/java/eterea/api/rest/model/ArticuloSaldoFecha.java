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
@Table(name = "articulosaldofecha", uniqueConstraints = { @UniqueConstraint(columnNames = { "asf_cst_id", "asf_art_id", "asf_fecha" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloSaldoFecha extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8603287140671704061L;

	@Column(name = "asf_cst_id")
	private Integer centroStockId;
	
	@Column(name = "asf_art_id")
	private String articuloId;
	
	@Column(name = "asf_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "asf_saldo")
	private BigDecimal saldo = BigDecimal.ZERO;
	
	@Id
	@Column(name = "clave")
	private Long articuloSaldoFechaId;

}

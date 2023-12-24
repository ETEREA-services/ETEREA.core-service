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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "inv_fecha", "inv_int_id", "inv_cst_id", "inv_art_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Inventario extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5748046852767307660L;

	@Column(name = "inv_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha; 
	
	@Column(name = "inv_int_id")
	private Integer inventarioTurnoId;
	
	@Column(name = "inv_cst_id")
	private Long centroStockId;
	
	@Column(name = "inv_art_id")
	private String articuloId;
	
	@Column(name = "inv_cantidad")
	private BigDecimal cantidad = BigDecimal.ZERO;
	
	@Column(name = "inv_stock")
	private BigDecimal stock = BigDecimal.ZERO;
	
	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventarioId;
	
}

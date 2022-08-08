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
@Table(name = "tarifapromoprecio", uniqueConstraints = { @UniqueConstraint(columnNames = { "tpp_tpr_id", "tpp_desde" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TarifaPromoPrecio extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2148774324574731344L;

	@Column(name = "tpp_tpr_id")
	private Integer tarifaPromoId;
	
	@Column(name = "tpp_desde")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime desde;
	
	@Column(name = "tpp_hasta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime hasta;
	
	@Column(name = "tpp_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Id
	@Column(name = "clave")
	private Long tarifaPromoPrecioId;
}

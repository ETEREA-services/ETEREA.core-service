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
@Table(name = "consfecha", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "cfe_neg_id", "cfe_fecha", "cfe_cuenta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidadoFecha extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8373270217722058477L;

	@Id
	@Column(name = "cfe_neg_id")
	private Integer negocioId;

	@Column(name = "cfe_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "cfe_cuenta")
	private Long cuenta;

	@Column(name = "cfe_deudor")
	private BigDecimal deudor = BigDecimal.ZERO;

	@Column(name = "cfe_acreedor")
	private BigDecimal acreedor = BigDecimal.ZERO;

}

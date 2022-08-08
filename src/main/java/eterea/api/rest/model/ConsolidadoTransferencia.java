/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "constransf", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "ctr_neg_id", "ctr_fecha", "ctr_cuenta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidadoTransferencia extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4652376906322273439L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consolidadoTransferenciaId;

	@Column(name = "ctr_neg_id")
	private Integer negocioId;

	@Column(name = "ctr_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "ctr_cuenta")
	private Long cuenta;

	@Column(name = "ctr_deudor")
	private BigDecimal deudor = BigDecimal.ZERO;

	@Column(name = "ctr_acreedor")
	private BigDecimal acreedor = BigDecimal.ZERO;

}

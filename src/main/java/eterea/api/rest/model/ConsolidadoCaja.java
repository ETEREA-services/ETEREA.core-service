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
@Table(name = "conscaja", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "cca_neg_id", "cca_cic_id", "cca_fecha", "cca_cuenta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidadoCaja extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1794105858634705366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consolidadoCajaId;

	@Column(name = "cca_neg_id")
	private Integer negocioId;

	@Column(name = "cca_cic_id")
	private Long cierreCajaId;

	@Column(name = "cca_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "cca_cuenta")
	private Long cuenta;

	@Column(name = "cca_deudor")
	private BigDecimal deudor = BigDecimal.ZERO;

	@Column(name = "cca_acreedor")
	private BigDecimal acreedor = BigDecimal.ZERO;

}

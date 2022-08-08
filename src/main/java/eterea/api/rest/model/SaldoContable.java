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
@Table(name = "saldocontable", uniqueConstraints = { @UniqueConstraint(columnNames = { "cuenta", "fecha" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SaldoContable extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -5977053839734013278L;

	@Column(name = "cuenta")
	private Long cuenta;
	
	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "debitos")
	private BigDecimal debitos = BigDecimal.ZERO;
	
	@Column(name = "creditos")
	private BigDecimal creditos = BigDecimal.ZERO;
	
	@Id
	@Column(name = "saldocontable_id")
	private Long saldoContableId;
}

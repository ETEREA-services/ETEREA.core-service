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
@Table(name = "tarifaprecio", uniqueConstraints = { @UniqueConstraint(columnNames = { "tap_tar_id", "tap_desde" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TarifaPrecio extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4743346914096564009L;

	@Column(name = "tap_tar_id")
	private Integer tarifaId;
	
	@Column(name = "tap_desde")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime desde;
	
	@Column(name = "tap_hasta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime hasta;
	
	@Column(name = "tap_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Id
	@Column(name = "clave")
	private Long tarifaPrecioId;
}

/**
 * 
 */
package eterea.core.api.rest.model;

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
@Table( uniqueConstraints = { @UniqueConstraint(columnNames = { "res_cuenta", "res_ctacte", "res_desde", "res_hasta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Resumen extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3909548653905789717L;

	@Id
	@Column(name = "res_cuenta")
	private Long cuenta;
	
	@Column(name = "res_ctacte")
	private Byte cuentaCorriente;
	
	@Column(name = "res_desde")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime desde;
	
	@Column(name = "res_hasta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime hasta;
	
	@Column(name = "res_total")
	private BigDecimal total = BigDecimal.ZERO;
	
	@Column(name = "res_eliminar")
	private Byte eliminar;
}

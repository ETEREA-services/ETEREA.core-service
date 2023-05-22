/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

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
@Table(name = "movconajuste", uniqueConstraints = { @UniqueConstraint(columnNames = { "mca_fecha", "mca_nrocomp" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoAjuste extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4095897425779071815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaMovimientoAjusteId;

	@Column(name = "mca_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mca_nrocomp")
	private Integer orden;
	
	@Column(name = "mca_contador")
	private Integer contador;
	
}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
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

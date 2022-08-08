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
@Table(name = "movconfirma")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoFirma extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 776028766047320257L;

	@Id
	@Column(name = "mcf_firma")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long firma;
	
	@Column(name = "mcf_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mcf_nrocomp")
	private Integer orden;
	
	@Column(name = "mcf_timestamp")
	private OffsetDateTime timestamp;
}

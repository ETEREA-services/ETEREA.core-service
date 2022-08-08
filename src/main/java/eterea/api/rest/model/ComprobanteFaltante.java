/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
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
@Table(name = "comprobfaltante", uniqueConstraints = { @UniqueConstraint(columnNames = { "cfa_neg_id", "cfa_cmp_id", "cfa_fecha", "cfa_prefijo", "cfa_numero" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteFaltante extends Auditable implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4704794534990511458L;

	@Id
	@Column(name = "cfa_neg_id")
	private Integer negocioId;
	
	@Column(name = "cfa_cmp_id")
	private Integer comprobanteId;
	
	@Column(name = "cfa_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "cfa_prefijo")
	private Integer prefijo;
	
	@Column(name = "cfa_numero")
	private Long numero;
	
	@Column(name = "cfa_id")
	private Long comprobanteFaltanteId;
}

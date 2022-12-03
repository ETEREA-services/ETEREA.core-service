/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "logo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Logo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4473590211183617885L;

	@Id
	@Column(name = "log_neg_id")
	private Integer negocioId;
	
	@Column(name = "log_size")
	private Long size;
	
	@Column(name = "log_imagen")
	private Blob imagen; 
	
}

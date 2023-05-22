/**
 * 
 */
package eterea.core.api.rest.model;

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
@Table(name = "prestadorlogo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PrestadorLogo extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -587450162906309651L;

	@Id
	@Column(name = "plo_pre_id")
	private Integer prestadorLogoId;
	
	@Column(name = "plo_size")
	private Long size;
	
	@Column(name = "plo_imagen")
	private Blob imagen;
}

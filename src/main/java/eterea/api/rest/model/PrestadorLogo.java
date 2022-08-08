/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

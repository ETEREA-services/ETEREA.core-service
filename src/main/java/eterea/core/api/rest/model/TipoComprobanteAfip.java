/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import eterea.core.api.rest.kotlin.model.Auditable;
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
@Table(name = "tiposcomprobafip")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TipoComprobanteAfip extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6375730930622555833L;

	@Id
	private Long tipoId;
	
	@Column(name = "compafip_id")
	private Integer comprobanteAfipId;
}

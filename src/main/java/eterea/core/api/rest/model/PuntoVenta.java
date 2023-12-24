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
@Table(name = "puntoventa")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PuntoVenta extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3845339345668494020L;

	@Id
	@Column(name = "pve_numero")
	private Integer numero;
	
	@Column(name = "pve_tm300")
	private Byte tm300;
	
	@Column(name = "pve_tm2000af")
	private Byte tm2000af;
	
	@Column(name = "pve_tmu220af")
	private Byte tmu220af;
	
}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

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
@Table(name = "movclieex")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteMovimientoExtranjero extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7019052783240902949L;

	@Id
	@Column(name = "mce_id")
	private Long clienteMovimientoExtranjeroId;

	@Column(name = "mce_mcl_id")
	private Long clienteMovimientoId;

	@Column(name = "mce_aci_id")
	private Integer mce_aci_id;

	@Column(name = "mce_ait_id")
	private Integer mce_ait_id;

	@Column(name = "mce_incotermsds")
	private String mce_incotermsds;

	@Column(name = "mce_apa_id")
	private Integer mce_apa_id;

	@Column(name = "mce_aid_id")
	private Integer mce_aid_id;

	@Column(name = "mce_formapago")
	private String formaPago;

	@Column(name = "mce_obscomerc")
	private String observacionesComerciales;

	@Column(name = "mce_observ")
	private String observaciones;

}

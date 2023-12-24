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
@Table(name = "tipohora")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TipoHora extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2057155938387231801L;

	@Id
	@Column(name = "tih_id")
	private Integer tipoHoraId; 
	
	@Column(name = "tih_nombre")
	private String nombre;
	
	@Column(name = "tih_generacomp")
	private Byte generaCompensatorio;
	
	@Column(name = "tih_color")
	private Long color;
	
	@Column(name = "tih_consumecomp")
	private Byte consumeCompensatorio;
	
	@Column(name = "tih_trabajada")
	private Byte trabajada;
}

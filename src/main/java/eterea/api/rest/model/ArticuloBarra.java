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
@Table(name = "articulosbarras")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloBarra extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4620826410596988827L;

	@Id
	@Column(name = "aba_codigo")
	private String codigo;
	
	@Column(name = "clave")
	private Long articuloBarraId;
	
	@Column(name = "aba_art_id")
	private String articuloId;
	
}

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
@Table(name = "tarifapromo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TarifaPromo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 961152929558695464L;

	@Id
	@Column(name = "tpr_id")
	private Integer tarifaPromoId;
	
	@Column(name = "tpr_nombre")
	private String nombre;
	
	@Column(name = "tpr_dias")
	private Integer dias;
	
	@Column(name = "tpr_art_id")
	private String articuloId;
}

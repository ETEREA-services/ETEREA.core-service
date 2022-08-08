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
@Table(name = "ibcontribuyente")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class IngresosBrutosContribuyente extends Auditable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8315825543421441633L;

	@Id
	@Column(name = "ibcontribuyente_id")
	private Integer ingresosBrutosContribuyenteId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "user_id")
	private String userId;
	
}

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
@Table(name = "centrosstock")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CentroStock extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 279145846728211613L;

	@Id
	@Column(name = "codigo")
	private Integer centroStockId;
	
	private String descripcion;
	
	@Column(name = "cst_neg_id")
	private Integer negocioId;
	
	@Column(name = "cst_contable")
	private Long cuenta;
	
	@Column(name = "cst_controlastock")
	private Byte controlaStock;
	
	@Column(name = "cst_tipo")
	private Integer tipo;
	
}

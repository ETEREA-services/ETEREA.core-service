/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "productocupo", uniqueConstraints = { @UniqueConstraint(columnNames = { "producto_id", "dias" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCupo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3684563996571907593L;

	@Column(name = "producto_id")
	private Integer productoId;
	
	@Column(name = "dias")
	private Integer dias; 
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Id
	@Column(name = "productocupo_id")
	private Long productoCupoId;
}

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
@Table(name = "mozos")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Mozo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -7598112726919963356L;

	@Id
	@Column(name = "moz_id")
	private Long mozoId;
	
	@Column(name = "moz_nombre")
	private String nombre;
}

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
@Table(name = "politica")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Politica extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2558360608552670379L;

	@Id
	@Column(name = "pol_id")
	private Integer politicaId;
	
	@Column(name = "pol_corto")
	private String corto;
	
	@Column(name = "pol_detalle")
	private String detalle;
}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

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
@Table(name = "plantilla")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Plantilla extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2051874210761737199L;

	@Id
	@Column(name = "pla_id")
	private Long planillaId;
	
	@Column(name = "pla_nombre")
	private String nombre;
	
	@Column(name = "pla_sec_id")
	private Integer sectorId;
	
	@Column(name = "pla_neg_id")
	private Integer negocioId;
}

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
@Table(name = "reservapolitica")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaPolitica extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2500623379039008588L;

	@Column(name = "reservaid")
	private Long reservaId;
	
	@Column(name = "prestadorid")
	private Integer prestadorId;
	
	@Column(name = "orden")
	private Integer orden;
	
	@Column(name = "politicaid")
	private Integer politicaId;
	
	@Id
	@Column(name = "id")
	private Long reservaPoliticaId;
}

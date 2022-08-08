/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(name = "tarifapromotarifa", uniqueConstraints = { @UniqueConstraint(columnNames = { "tpt_tar_id", "tpt_dias" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TarifaPromoTarifa extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1456028347711828309L;

	@Column(name = "tpt_tar_id")
	private Integer tarifaId;
	
	@Column(name = "tpt_dias")
	private Integer dias;
	
	@Id
	@Column(name = "tpt_tpr_id")
	private Integer tarifaPromoTarifaId;
}

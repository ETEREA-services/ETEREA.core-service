/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

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
@Table(name = "respafip")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableAfip extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2815519667810643775L;

	@Id
	private Integer responsableId;
	private String nombre;
	private Long autoId;
}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "habitaciontarifa", uniqueConstraints = { @UniqueConstraint(columnNames = { "hta_numero", "hta_paxs" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionTarifa extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4244799733579849998L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hta_id")
	private Long habitacionTarifaId;

	@Column(name = "hta_numero")
	private Integer numero = 0;

	@Column(name = "hta_paxs")
	private Integer paxs = 0;

	@Column(name = "hta_hat_id")
	private Integer habitacionTipoId = 0;

}

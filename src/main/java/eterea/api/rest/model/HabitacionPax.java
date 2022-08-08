/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "habitacionpax", uniqueConstraints = { @UniqueConstraint(columnNames = { "habitacionId", "pax", "tipoId" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionPax extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3088333167000177402L;

	private Long habitacionId;
	private Integer pax;
	private Integer tipoId;
	private Integer productoId;
	
	@Id
	@Column(name = "habitacionpax_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long habitacionPaxId;
}

/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import eterea.core.api.rest.kotlin.model.Auditable;
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

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
@Table(name = "reservaproducto", uniqueConstraints = { @UniqueConstraint(columnNames = { "reserva_id", "producto_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaProducto extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9051221636123592384L;

	@Column(name = "reserva_id")
	private Long reservaId;
	
	@Column(name = "producto_id")
	private Integer productoId;
	
	@Column(name = "pax")
	private Integer pax;
	
	@Id
	@Column(name = "reservaproducto_id")
	private Long reservaProductoId;
}

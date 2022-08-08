/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name = "hotel")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1395302752759135891L;

	@Id
	@Column(name = "hot_id")
	private Integer hotelId;

	@Column(name = "hot_nombre")
	@NotNull
	@Size(max = 100)
	private String nombre = "";

	@Column(name = "extras")
	@NotNull
	private Byte extras = 0;

	@Column(name = "parada_traslado")
	@NotNull
	private Byte paradatraslado = 0;

	@Column(name = "punto_encuentro")
	@NotNull
	private Byte puntoencuentro = 0;

}

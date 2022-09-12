/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author daniel
 *
 */
@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
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
	private String nombre = "";

	private Byte extras = 0;
	private Byte paradaTraslado = 0;
	private Byte puntoEncuentro = 0;

}

/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "violacionlimite")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ViolacionLimite extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1276425803404713928L;

	@Id
	@Column(name = "vli_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "violacionlimite_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long violacionLimiteId;
	
}

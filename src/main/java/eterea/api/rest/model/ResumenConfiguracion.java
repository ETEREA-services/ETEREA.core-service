/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "resuconfig")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ResumenConfiguracion extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8266901120385629659L;

	@Id
	@Column(name = "rec_cuenta")
	private Long cuenta;
	
	@Column(name = "rec_tipo")
	private Integer tipo;
}

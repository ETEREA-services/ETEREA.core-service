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
@Table
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Reflex extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2716150240168662741L;

	@Id
	@Column(name = "clave")
	private Integer reflexId;
	
	@Column(name = "ref_database")
	private String database;
	
	@Column(name = "ref_ip")
	private String ipAddress;
	
	@Column(name = "ref_user")
	private String user;
}

/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
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
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -5989447252314912282L;

	@Id
	@Column(name = "id")
	private Long userId;
	
	private Byte enabled;
	private String password;
	private String username;
}

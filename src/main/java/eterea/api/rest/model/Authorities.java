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
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "authorities", uniqueConstraints = { @UniqueConstraint(columnNames = { "cli_internet_id" }) })
@NoArgsConstructor
@AllArgsConstructor
public class Authorities implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5365067307934008404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "cli_internet_id")
	private Long clienteId;

	@Column(name = "authority")
	private String authority;

}

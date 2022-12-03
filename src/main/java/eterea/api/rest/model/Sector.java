/**
 * 
 */
package eterea.api.rest.model;

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
@Table(name = "sector")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Sector extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6837765166727097001L;

	@Id
	@Column(name = "sec_id")
	private Integer sectorId;
	
	@Column(name = "sec_nombre")
	private String nombre;
}

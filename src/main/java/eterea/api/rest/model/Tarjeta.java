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
@Table(name = "tarjeta", uniqueConstraints = { @UniqueConstraint(columnNames = { "tar_leg_id", "tar_anho", "tar_mes" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta extends Auditable implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 9111489507733022645L;

	@Column(name = "tar_leg_id")
	private Integer legajoId;
	
	@Column(name = "tar_anho")
	private Integer anho;
	
	@Column(name = "tar_mes")
	private Integer mes;
	
	@Column(name = "tar_numero")
	private Long numero;
	
	@Column(name = "tar_sec_id")
	private Integer sectorId;
	
	@Column(name = "tar_observ")
	private String observaciones;
	
	@Id
	@Column(name = "tar_id")
	private Long tarjetaId;
}

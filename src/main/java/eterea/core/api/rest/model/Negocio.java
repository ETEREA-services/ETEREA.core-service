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
 * @author daniel
 *
 */
@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Negocio extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 816078638050323268L;

	@Id
	@Column(name = "neg_id")
	private Integer negocioId;

	@Column(name = "neg_nombre")
	private String nombre;

	@Column(name = "neg_id_real")
	private Integer negocioIdReal;

	@Column(name = "neg_ip")
	private String ipAddress;

	@Column(name = "neg_db")
	private String database;

	@Column(name = "neg_user")
	private String user;

	@Column(name = "neg_transfstock")
	private Byte transferenciaStock = 0;

	@Column(name = "neg_transfvalor")
	private Byte transferenciaValor = 0;

	private String backendServer;
	private String backendPort;

}

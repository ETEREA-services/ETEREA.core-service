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
@Table(name = "umedida")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedida extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3819676321613049055L;

	@Id
	@Column(name = "ume_id")
	private Long unidadMedidaId;
	
	@Column(name = "ume_nombre")
	private String nombre;
	
	@Column(name = "ume_corto")
	private String corto;
}

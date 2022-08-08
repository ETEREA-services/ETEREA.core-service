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
@Table(name = "articulossubrubro", uniqueConstraints = { @UniqueConstraint(columnNames = { "cgorubro", "codigo" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloSubrubro extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4045581131052221466L;

	@Column(name = "cgorubro")
	private Long articuloRubroId;
	
	@Column(name = "codigo")
	private Long articuloSubrubroId;
	
	@Column(name = "asr_neg_id")
	private Integer negocioId;
	
	private String descripcion;
	
	@Column(name = "asr_cot_id")
	private Integer cotizacionId;
	
	@Column(name = "asr_cgocontabledebe")
	private Long cuentaDebe;
	
	@Column(name = "asr_cgocontablehaber")
	private Long cuentaHaber;
	
	@Id
	@Column(name = "clave")
	private Long uniqueId;
	
}

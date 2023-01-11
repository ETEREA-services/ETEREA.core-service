/**
 * REVISAR: Entidad de la tabla articulosbarras, en esto momentos sin datos en la BD aparentemente seria
 * una relacion entre articulo y su codigo de barra, de ser asi se recomienda cambiar el tipo de
 * de atributoId de String a Articulo asi establecer una relacion One to One o lo que seria mas
 * natural que la entidad Articulo tenga un atributo CodigoDeBarra.-
 */
package eterea.api.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "articulosbarras")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ArticuloBarra extends Auditable implements Serializable {

	private static final long serialVersionUID = -4620826410596988827L;

	@Id
	@Column(name = "aba_codigo")
	private String codigoBarras;
	
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long articuloBarraId;
	
	@Column(name = "aba_art_id")
	private String articuloId;

	@OneToOne(optional = true)
	@JoinColumn(name = "aba_art_id", insertable = false, updatable = false)
	private Articulo articulo;
	
}

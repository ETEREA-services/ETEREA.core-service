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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "producto")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Producto extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 828730990653109560L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prd_id")
	private Integer productoId;

	@Column(name = "prd_nombre")
	@Size(max = 100)
	@NotNull
	private String nombre = "";

	@Column(name = "prd_observaciones")
	@Size(max = 200)
	@NotNull
	private String observaciones = "";

	@Column(name = "prd_deshabilitado")
	@NotNull
	private Byte deshabilitado = 0;

	@Column(name = "traslado")
	@NotNull
	private Byte traslado = 0;

	@Column(name = "punto_encuentro")
	@NotNull
	private Byte puntoencuentro = 0;

	@Column(name = "venta_mostrador")
	@NotNull
	private Byte ventamostrador = 0;

	@Column(name = "venta_internet")
	@NotNull
	private Byte ventainternet = 0;

}
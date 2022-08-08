/**
 * 
 */
package eterea.api.rest.model.view;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import eterea.api.rest.model.view.pk.ProductoCantidadPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "vw_prodcantidad")
@IdClass(value = ProductoCantidadPk.class)
@Immutable
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCantidad implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3982140438678073329L;
	
	@Id
	@Column(name = "vou_fechain")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaServicio;

	@Id
	@Column(name = "prd_id")
	private Integer productoId;

	@Column(name = "prd_nombre")
	private String nombre;
	
	private Integer cantidad;

}

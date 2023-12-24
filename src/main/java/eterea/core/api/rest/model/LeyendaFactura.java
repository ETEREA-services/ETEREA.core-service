/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "leyendafactura")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LeyendaFactura extends Auditable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6104835924527700899L;

	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leyendaFacturaId;
	
	@Column(name = "constante")
	private Long constante;
	
	@Column(name = "linea")
	private String linea;
	
}

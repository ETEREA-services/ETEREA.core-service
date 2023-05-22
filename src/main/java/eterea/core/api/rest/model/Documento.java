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
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "documento")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Documento extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5415041042414104522L;

	@Id
	@Column(name = "doc_id")
	private Integer documentoId;
	
	@Column(name = "doc_nombre")
	private String nombre;
	
	@Column(name = "doc_reducido")
	private String reducido;
	
	private Integer documentoIdAfip;
	
}	

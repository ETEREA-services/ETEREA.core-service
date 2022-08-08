/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

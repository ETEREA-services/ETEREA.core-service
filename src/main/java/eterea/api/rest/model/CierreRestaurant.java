/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "cierrerest")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CierreRestaurant extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1751024413942491962L;

	@Id
	@Column(name = "cir_id")
	private Long cierreRestaurantId;
	
	@Column(name = "cir_neg_id")
	private Integer negocioId;
	
	@Column(name = "cir_fechahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHora;
	
	@Column(name = "cir_usuario")
	private String usuario;
	
	@Column(name = "cir_nivel")
	private Integer nivel;
	
}

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
@Table(name = "cierrecaja")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CierreCaja extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3176171648818295644L;

	@Id
	@Column(name = "cic_id")
	private Long cierreCajaId;
	
	private Integer puntoVenta;
	
	@Column(name = "cic_neg_id")
	private Integer negocioId;
	
	@Column(name = "cic_tope")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime tope;
	
	@Column(name = "cic_fechahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHora;
	
	private String usuario;
	
	@Column(name = "cic_nivel")
	private Integer nivel;
	
	@Column(name = "cic_dneg_id")
	private Integer negocioIdDesde;
	
	@Column(name = "cic_hneg_id")
	private Integer negocioIdHasta;
	
	@Column(name = "cic_tra_id")
	private Long transferenciaId;
	
	@Column(name = "cic_enviado")
	private Byte enviado;

}

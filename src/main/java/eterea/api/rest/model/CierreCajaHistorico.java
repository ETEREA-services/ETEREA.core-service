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
@Table(name = "cierrecajahist")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaHistorico extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8527721699567101437L;

	@Id
	@Column(name = "cch_id")
	private Long cierreCajaHistoricoId;
	
	@Column(name = "cch_neg_id")
	private Integer negocioId;
	
	@Column(name = "cch_cic_id")
	private Long cierreCajaId;
	
	private Integer puntoVenta;
	
	@Column(name = "coh_tope")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime tope;
	
	@Column(name = "cch_fechahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHora;
	
	@Column(name = "cch_usuario")
	private String usuario;
	
	@Column(name = "cch_nivel")
	private Integer nivel;
	
	@Column(name = "cch_dneg_id")
	private Integer negocioIdDesde;
	
	@Column(name = "cch_hneg_id")
	private Integer negocioIdHasta;
	
	@Column(name = "cch_tra_id")
	private Long transferenciaId;
	
	@Column(name = "cch_enviado")
	private Byte enviado;
	
}
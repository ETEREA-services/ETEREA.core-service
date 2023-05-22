/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "reservacomision")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaComision extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -597064322403761606L;

	@Column(name = "rec_res_id")
	private Long reservaId;
	
	@Column(name = "rcn_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "rcn_vou_id")
	private Long voucherId;
	
	@Column(name = "rcn_paxsmay")
	private Integer paxsMayor;
	
	@Column(name = "rcn_paxsmen")
	private Integer paxsMenor;
	
	@Column(name = "rcn_paxsfree")
	private Integer paxsFree;
	
	@Column(name = "rcn_importeindivmay")
	private BigDecimal importeIndividualMayor = BigDecimal.ZERO;
	
	@Column(name = "rcn_importeindivmen")
	private BigDecimal importeIndividualMenor = BigDecimal.ZERO;
	
	@Column(name = "rcn_porccomision")
	private BigDecimal porcentajeComision = BigDecimal.ZERO;
	
	@Column(name = "rcn_porccomisionmen")
	private BigDecimal porsentajeComisionMenor = BigDecimal.ZERO;
	
	@Column(name = "rcn_comision")
	private BigDecimal comision = BigDecimal.ZERO;
	
	@Column(name = "rcn_letras")
	private String letras;
	
	@Column(name = "rcn_numero")
	private Long numero;
	
	@Id
	@Column(name = "clave")
	private Long reservaComisionId;
}

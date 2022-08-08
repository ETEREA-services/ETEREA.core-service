/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Romaneo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -952967088069706478L;

	@Id
	@Column(name = "clave")
	private Long romaneoId;
	
	@Column(name = "rom_neg_id")
	private Integer negocioId;
	
	@Column(name = "rom_cic_id")
	private Long cierreCajaId;
	
	@Column(name = "rom_mon_id")
	private Integer monedaId;
	
	@Column(name = "rom_grupo")
	private Integer grupo;
	
	@Column(name = "rom_denominacion")
	private BigDecimal denominacion = BigDecimal.ZERO;
	
	@Column(name = "rom_cantidad")
	private Integer cantidad;
	
	@Column(name = "rom_cotizacion")
	private BigDecimal cotizacion = BigDecimal.ZERO;
	
	@Column(name = "rom_dneg_id")
	private Integer negocioIdDesde;
	
	@Column(name = "rom_hneg_id")
	private Integer negocioIdHasta;
	
	@Column(name = "rom_tra_id")
	private Long transferenciaId;
}

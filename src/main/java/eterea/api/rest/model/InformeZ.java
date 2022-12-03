/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "informez", uniqueConstraints = { @UniqueConstraint(columnNames = { "inz_neg_id", "inz_ptovta", "inz_id" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InformeZ extends Auditable implements Serializable  {/**
	 * 
	 */
	private static final long serialVersionUID = 2700398898152166211L;

	@Column(name = "inz_neg_id")
	private Integer negocioId;
	
	@Column(name = "inz_ptovta")
	private Integer puntoVenta;
	
	@Column(name = "inz_id")
	private Long informezId;
	
	@Column(name = "inz_fecha")
	private OffsetDateTime fecha;
	
	@Column(name = "inz_hora")
	private OffsetDateTime hora;
	
	@Column(name = "inz_ventadiario")
	private BigDecimal ventaDiario = BigDecimal.ZERO;
	
	@Column(name = "inz_ivadiario")
	private BigDecimal ivaDiario = BigDecimal.ZERO;
	
	@Column(name = "inz_ultimoa")
	private Long ultimoA;
	
	@Column(name = "inz_ultimob")
	private Long ultimoB;
	
	@Column(name = "inz_emitidos")
	private Integer emitidos;
	
	@Column(name = "inz_cancelados")
	private Integer cancelados;
	
	@Column(name = "total_nc")
	private BigDecimal totalNotaCredito = BigDecimal.ZERO; 
	
	@Column(name = "iva_nc")
	private BigDecimal ivaNotaCredito = BigDecimal.ZERO;
	
	@Column(name = "ultima_a")
	private Long ultimaA;
	
	@Column(name = "ultima_bc")
	private Long ultimaBc;

	@Column(name = "emitidas_nc")
	private Integer emitidasNotaCredito;

	@Column(name = "inz_cic_id")
	private Long cierreCajaId;
	
	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clave;
	
}

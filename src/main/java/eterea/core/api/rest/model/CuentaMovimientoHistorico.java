/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "movconhist")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoHistorico extends Auditable implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2740182462532830246L;

	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaMovimientoHistoricoId;
	
	@Column(name = "mch_mcf_firma")
	private Long firma;
	
	@Column(name = "mch_orden")
	private Integer orden;
	
	@Column(name = "mch_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;
	
	@Column(name = "mch_nrocomp")
	private Integer ordenContable;
	
	@Column(name = "mch_neg_id")
	private Integer negocioId;
	
	@Column(name = "mch_cuenta")
	private Integer cuenta;
	
	@Column(name = "mch_cmp_id")
	private Integer comprobanteId;
	
	@Column(name = "mch_item")
	private Integer item;
	
	@Column(name = "mch_concepto")
	private String concepto;
	
	@Column(name = "mch_debita")
	private Byte debita;
	
	@Column(name = "mch_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "mch_cgosub")
	private Long subrubroId;
	
	@Column(name = "mch_cgoprov")
	private Long proveedorId;
	
	@Column(name = "mch_cgoclie")
	private Long clienteId;
	
	@Column(name = "mch_movimiento")
	private String movimiento;
	
	private Integer ejercicioId;
	private Byte inflacion;

}

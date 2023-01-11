/**
 * 
 */
package eterea.api.rest.model;

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
@Table(name = "cgosvalores")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Valor extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 9128594883400872207L;

	@Id
	@Column(name = "codigo")
	private Integer valorId;
	
	private String concepto;
	
	@Column(name = "cva_neg_id")
	private Integer negocioId;
	
	@Column(name = "modulocompras")
	private Byte compras = 0;
	
	@Column(name = "moduloventas")
	private Byte ventas = 0;
	
	private Byte numerable = 0;
	private Byte duplicados = 0;
	
	@Column(name = "fechaemi")
	private Byte fechaEmision = 0;
	
	@Column(name = "fechavto")
	private Byte fechaVencimiento = 0;
	
	@Column(name = "cgocontable")
	private Long numeroCuenta;
	
	private Byte titular = 0;
	private Byte banco = 0;
	
	@Column(name = "chtercero")
	private Byte chequeTercero = 0;
	
	@Column(name = "ctacte")
	private Byte cuentaCorriente = 0;
	
	@Column(name = "retib")
	private Byte retencionIngresosBrutos = 0;
	
	@Column(name = "cva_retgcias")
	private Byte retencionGanancias = 0;
	
	@Column(name = "cva_retiva")
	private Byte retencionIva = 0;
	
	@Column(name = "cva_autonumerable")
	private Byte autoNumerable = 0;
	
	@Column(name = "cva_mon_id")
	private Integer monedaId;
	
	@Column(name = "cva_invisible")
	private Byte invisible = 0;
	
	private Byte restaurant = 0;
	private Byte tarjeta = 0;
	
}
/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import eterea.core.api.rest.kotlin.model.Auditable;
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
	private Byte compras;
	
	@Column(name = "moduloventas")
	private Byte ventas;
	
	private Byte numerable;
	private Byte duplicados;
	
	@Column(name = "fechaemi")
	private Byte fechaEmision;
	
	@Column(name = "fechavto")
	private Byte fechaVencimiento;
	
	@Column(name = "cgocontable")
	private Long cuenta;
	
	private Byte titular;
	private Byte banco;
	
	@Column(name = "chtercero")
	private Byte chequeTercero;
	
	@Column(name = "ctacte")
	private Byte cuentaCorriente;
	
	@Column(name = "retib")
	private Byte retencionIngresosBrutos;
	
	@Column(name = "cva_retgcias")
	private Byte retencionGanancias;
	
	@Column(name = "cva_retiva")
	private Byte retencionIva;
	
	@Column(name = "cva_autonumerable")
	private Byte autoNumerable;
	
	@Column(name = "cva_mon_id")
	private Integer monedaId;
	
	@Column(name = "cva_invisible")
	private Byte invisible;
	
	private Byte restaurant;
	private Byte tarjeta;
	
}
/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "parametros")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Parametro extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 7370275537625150684L;

	@Id
	@Column(name = "clave")
	private Long parametroId;
	
	@Column(name = "par_neg_id")
	private Integer negocioId; 
	
	@Column(name = "ctaprov")
	private Long numeroCuentaProveedor;
	
	@Column(name = "ctaiva21compras")
	private Long numeroCuentaIva21Compras;
	
	@Column(name = "ctaiva27compras")
	private Long numeroCuentaIva27Compras;
	
	@Column(name = "ctaiva105compras")
	private Long numeroCuentaIva105Compras;
	
	@Column(name = "ctaperivacompras")
	private Long numeroCuentaPercepcionIvaCompras;
	
	@Column(name = "ctaperingbrutoscompras")
	private Long numeroCuentaPercepcionIngresosBrutosCompras;
	
	@Column(name = "ctagngcompras")
	private Long numeroCuentaGastosNoGravadosCompras;
	
	@Column(name = "ctaclientes")
	private Long numeroCuentaClientes;
	
	@Column(name = "ctaivaventas")
	private Long numeroCuentaIvaVentas;
	
	@Column(name = "ctaivarniventas")
	private Long numeroCuentaIvaRniVentas;
	
	@Column(name = "ctaventas")
	private Long numeroCuentaVentas;
	
	@Column(name = "ctacaja")
	private Long numeroCuentaCaja;
	
	@Column(name = "ctaajuste")
	private Long numeroCuentaAjuste;
	
	@Column(name = "cuenta_stock_confirmar")
	private Long numeroCuentaStockConfirmar;
	
	private BigDecimal iva1 = BigDecimal.ZERO;
	private BigDecimal iva2 = BigDecimal.ZERO;
	
	@Column(name = "ivacre1")
	private BigDecimal ivaCredito1 = BigDecimal.ZERO;
	
	@Column(name = "bloqueoivacompras")
	private Byte bloqueIvaCompras = 0;
	
	@Column(name = "ivavta")
	private BigDecimal ivaVenta = BigDecimal.ZERO;
	
	@Column(name = "par_cst_id_ingreso")
	private Integer centroStockIdIngreso;
	
	@Column(name = "par_cst_id_restaurant")
	private Integer  centroStockIdRestaurant;
	
	@Column(name = "par_feproduccion")
	private Byte facturaElectronicaProduccion = 0;
	
}

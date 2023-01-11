/**
 * TO DO: Revisar la logica de negocio sobre todo en las relaciones y
 * platearse una migracion completa de las tablas hacia un modelo relacional
 * mas abstraido y funcional
 *
 * Entidad de la tabla Cliente, relacionada con las tablas
 *  * clientegrupocupo, clienteinternet, clientemovimientoprevio,
 *  * habitacion, productoclientecomision,
 **/
package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Cliente extends Auditable implements Serializable {

	private static final long serialVersionUID = 533560218390771492L;

	@Id
	@Column(name = "codigo")
	private Long clienteId;

	private String nombre;

	@Column(name = "cli_neg_id")
	private Integer negocioId;

	private String cuit = "";

	@Column(name = "razon")
	private String razonSocial = "";

	@Column(name = "cli_fantasia")
	private String nombreFantasia = "";

	@Column(name = "cli_fecharestaurant")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaRestaurant;

	@Column(name = "cli_cantpaxs")
	private Integer cantidadPaxs = 0;

	@Column(name = "cli_tipo")
	private Integer tipoCliente = 0;

	private String domicilio = "";

	@Column(name = "tel")
	private String telefono = "";

	private String fax = "";
	private String email = "";

	@Column(name = "celular")
	private String numeroMovil = "";

	@Column(name = "posicion")
	private Integer posicionIva = 0;

	private Integer constante = 0;
	private Integer documentoId = 0;

	@Column(name = "tipodoc")
	private String tipoDocumento = "";

	@Column(name = "nrodoc")
	private String numeroDocumento = "";

	@Column(name = "limitecredito")
	private BigDecimal limiteCredito = BigDecimal.ZERO;

	private String nacionalidad = "";

	@Column(name = "cli_cca_id")
	private Integer clienteCategoriaId;

	@Column(name = "cli_idimpositivo")
	private String impositivoId = "";

	@Column(name = "facturarextranjero")
	private Byte facturarExtranjero = 0;

	private Byte bloqueado = 0;
	private Byte discapacitado = 0;

}

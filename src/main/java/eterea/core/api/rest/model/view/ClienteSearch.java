/**
 * 
 */
package eterea.core.api.rest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Immutable
@Table(name = "vw_clienteactivado")
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSearch implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1822618521838986604L;

	@Id
	@Column(name = "codigo")
	private Long clienteId;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "cli_neg_id")
	private Integer negocioId;

	@Column(name = "cuit")
	@NotNull
	private String cuit = "";

	@Column(name = "razon")
	@Size(max = 60)
	@NotNull
	private String razonSocial = "";

	@Column(name = "cli_fantasia")
	@Size(max = 100)
	@NotNull
	private String nombreFantasia;

	@Column(name = "cli_fecharestaurant")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaRestaurant;

	@Column(name = "cli_cantpaxs")
	@NotNull
	private Integer cantidadPaxs = 0;

	@Column(name = "cli_tipo")
	@NotNull
	private Integer tipoCliente = 0;

	@Column(name = "domicilio")
	@Size(max = 100)
	@NotNull
	private String domicilio = "";

	@Column(name = "tel")
	@Size(max = 20)
	@NotNull
	private String telefono = "";

	@Column(name = "fax")
	@Size(max = 20)
	@NotNull
	private String fax;

	@Column(name = "email")
	@Size(max = 40)
	@NotNull
	private String email = "";

	@Column(name = "celular")
	@Size(max = 40)
	@NotNull
	private String numeroMovil;

	@Column(name = "posicion")
	@NotNull
	private Integer posicionIva = 0;

	@Column(name = "constante")
	@NotNull
	private Integer constante = 0;

	@Column(name = "documento_id")
	@NotNull
	private Integer documentoId = 0;

	@Column(name = "tipodoc")
	@Size(max = 50)
	@NotNull
	private String tipoDocumento = "";

	@Column(name = "nrodoc")
	@Size(max = 50)
	@NotNull
	private String numeroDocumento;

	@Column(name = "limitecredito")
	@NotNull
	private BigDecimal limiteCredito = new BigDecimal(0);

	@Column(name = "nacionalidad")
	@Size(max = 50)
	@NotNull
	private String nacionalidad;

	@Column(name = "cli_cca_id")
	private Integer clienteCategoriaId;

	@Column(name = "cli_idimpositivo")
	@Size(max = 50)
	@NotNull
	private String impositivoId = "";

	@Column(name = "facturarextranjero")
	@NotNull
	private Byte facturarExtranjero = 0;

	@Column(name = "bloqueado")
	@NotNull
	private Byte bloqueado = 0;

	@Column(name = "discapacitado")
	@NotNull
	private Byte discapacitado = 0;

	@Column(name = "search")
	private String search;

}

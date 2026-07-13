package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private Long clienteId;
    private String nombre;
    private Integer negocioId;
    private String cuit;
    private String razonSocial;
    private String nombreFantasia;
    private OffsetDateTime fechaRestaurant;
    private Integer cantidadPaxs;
    private Integer tipoCliente;
    private String domicilio;
    private String telefono;
    private String fax;
    private String email;
    private String numeroMovil;
    private Integer posicionIva;
    private Integer constante;
    private Integer documentoId;
    private String tipoDocumento;
    private String numeroDocumento;
    private BigDecimal limiteCredito;
    private String nacionalidad;
    private Integer clienteCategoriaId;
    private String idImpositivo;
    private Byte facturarExtranjero;
    private Byte bloqueado;
    private Byte discapacitado;

}

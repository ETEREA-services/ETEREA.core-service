package eterea.core.service.hexagonal.empresa.infrastructure.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class EmpresaResponse {

    private Integer empresaId;
    private Integer negocioId;
    private String razonSocial;
    private String nombreFantasia;
    private String domicilio;
    private String telefono;
    private String cuit;
    private String ingresosBrutos;
    private String numeroEstablecimiento;
    private String sedeTimbrado;
    private String inicioActividades;
    private String condicionIva;
    private Byte unidadNegocio;
    private Integer diaInicial;
    private Integer negocioIdComision;
    private Byte conectaUnificado;
    private String certificado;
    private UUID businessId;

}

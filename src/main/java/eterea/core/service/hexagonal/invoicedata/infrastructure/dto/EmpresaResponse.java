package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmpresaResponse {

    private String nombreFantasia;
    private String razonSocial;
    private String domicilio;
    private String telefono;
    private String condicionIva;
    private String cuit;
    private String ingresosBrutos;
    private String inicioActividades;

}

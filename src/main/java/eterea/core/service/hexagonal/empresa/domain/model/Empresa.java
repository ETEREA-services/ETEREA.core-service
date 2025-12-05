package eterea.core.service.hexagonal.empresa.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Builder.Default
    private Integer empresaId = 1;
    @Builder.Default
    private Integer negocioId = 0;
    @Builder.Default
    private String razonSocial = "";
    @Builder.Default
    private String nombreFantasia = "";
    @Builder.Default
    private String domicilio = "";
    @Builder.Default
    private String telefono = "";
    @Builder.Default
    private String cuit = "";
    @Builder.Default
    private String ingresosBrutos = "";
    @Builder.Default
    private String numeroEstablecimiento = "";
    @Builder.Default
    private String sedeTimbrado = "";
    @Builder.Default
    private String inicioActividades = "";
    @Builder.Default
    private String condicionIva = "";
    @Builder.Default
    private Byte unidadNegocio = 0;
    @Builder.Default
    private Integer diaInicial = 0;
    @Builder.Default
    private Integer negocioIdComision = 0;
    @Builder.Default
    private Byte conectaUnificado = 0;
    @Builder.Default
    private String certificado = "";
    private UUID businessId;

}

package eterea.core.service.hexagonal.compras.proveedor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    private Integer proveedorId;

    @Builder.Default
    private String razonSocial = "";

    private Integer negocioId;

    @Builder.Default
    private String cuit = "";

    @Builder.Default
    private String domicilio = "";

    @Builder.Default
    private String telefono = "";

    @Builder.Default
    private String fax = "";

    @Builder.Default
    private String email = "";

    @Builder.Default
    private Integer posicion = 0;

    @Builder.Default
    private String celular = "";

    @Builder.Default
    private String numeroInscripcionIngresosBrutos = "";

    private Integer ingresosBrutosContribuyenteId;

    private Integer proveedorCategoriaId;

    private Integer ingresosBrutosCategoriaId;

    private Integer reparticionId;

    @Builder.Default
    private Byte transporte = 0;

}

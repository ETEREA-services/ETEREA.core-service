package eterea.core.service.hexagonal.compras.proveedor.infrastructure.web.dto;

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
public class ProveedorResponse {

    private Integer proveedorId;
    private String razonSocial;
    private Integer negocioId;
    private String cuit;
    private String domicilio;
    private String telefono;
    private String fax;
    private String email;
    private Integer posicion;
    private String celular;
    private String numeroInscripcionIngresosBrutos;
    private Integer ingresosBrutosContribuyenteId;
    private Integer proveedorCategoriaId;
    private Integer ingresosBrutosCategoriaId;
    private Integer reparticionId;
    private Byte transporte;

}

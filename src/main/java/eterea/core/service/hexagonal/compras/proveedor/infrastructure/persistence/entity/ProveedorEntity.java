package eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.entity;

import eterea.core.service.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity extends Auditable {

    @Id
    @Column(name = "codigo")
    private Integer proveedorId;

    @Builder.Default
    @Column(name = "razon")
    private String razonSocial = "";

    @Column(name = "Prv_Neg_ID")
    private Integer negocioId;

    @Builder.Default
    private String cuit = "";

    @Builder.Default
    private String domicilio = "";

    @Builder.Default
    @Column(name = "tel")
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
    @Column(name = "nroinscib")
    private String numeroInscripcionIngresosBrutos = "";

    @Column(name = "ibcontribuyente_id")
    private Integer ingresosBrutosContribuyenteId;

    @Column(name = "prv_prc_id")
    private Integer proveedorCategoriaId;

    @Column(name = "ibcategoria_id")
    private Integer ingresosBrutosCategoriaId;

    @Column(name = "prv_rep_codigo")
    private Integer reparticionId;

    @Builder.Default
    private Byte transporte = 0;

}

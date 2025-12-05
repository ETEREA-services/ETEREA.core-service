package eterea.core.service.hexagonal.empresa.infrastructure.persistence.entity;

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

import java.util.UUID;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaEntity extends Auditable {

    @Id
    @Column(name = "emp_id")
    private Integer empresaId;

    @Column(name = "emp_neg_id")
    private Integer negocioId;

    @Column(name = "nombre")
    private String razonSocial;

    @Column(name = "emp_fantasia")
    private String nombreFantasia;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "telf")
    private String telefono;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "ingbrutos")
    private String ingresosBrutos;

    @Column(name = "nroestablecimiento")
    private String numeroEstablecimiento;

    @Column(name = "sedetimbrado")
    private String sedeTimbrado;

    @Column(name = "inicioactividades")
    private String inicioActividades;

    @Column(name = "condicioniva")
    private String condicionIva;

    @Column(name = "unegocio")
    private Byte unidadNegocio;

    @Column(name = "emp_diainicial")
    private Integer diaInicial;

    @Column(name = "emp_neg_id_comision")
    private Integer negocioIdComision;

    @Column(name = "emp_conectaunif")
    private Byte conectaUnificado;

    private String certificado;
    private UUID businessId;

}

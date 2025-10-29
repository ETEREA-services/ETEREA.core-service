package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
data class Empresa(

    @Id @Column(name = "emp_id")
    var empresaId: Int? = null,

    @Column(name = "emp_neg_id")
    var negocioId: Int? = null,

    @Column(name = "nombre")
    var razonSocial: String? = null,

    @Column(name = "emp_fantasia")
    var nombreFantasia: String? = null,

    @Column(name = "domicilio")
    var domicilio: String? = null,

    @Column(name = "telf")
    var telefono: String? = null,

    @Column(name = "cuit")
    var cuit: String? = null,

    @Column(name = "ingbrutos")
    var ingresosBrutos: String? = null,

    @Column(name = "nroestablecimiento")
    var numeroEstablecimiento: String? = null,

    @Column(name = "sedetimbrado")
    var sedeTimbrado: String? = null,

    @Column(name = "inicioactividades")
    var inicioActividades: String? = null,

    @Column(name = "condicioniva")
    var condicionIva: String? = null,

    @Column(name = "unegocio")
    var unidadNegocio: Byte? = null,

    @Column(name = "emp_diainicial")
    var diaInicial: Int? = null,

    @Column(name = "emp_neg_id_comision")
    var negocioIdComision: Int? = null,

    @Column(name = "emp_conectaunif")
    var conectaUnificado: Byte? = null,

    @Column(name = "certificado")
    var certificado: String? = null

) : Auditable()

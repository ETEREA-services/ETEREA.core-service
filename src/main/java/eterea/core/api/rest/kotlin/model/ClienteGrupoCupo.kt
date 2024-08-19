package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(
    name = "clientegrupocupo",
    uniqueConstraints = [UniqueConstraint(columnNames = ["cliente_id", "grupo_id", "dias"])]
)
data class ClienteGrupoCupo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientegrupocupo_id")
    var clientegrupocupoId: Long? = null,

    @Column(name = "cliente_id")
    var clienteId: Long? = null,

    @Column(name = "grupo_id")
    var grupoId: Int? = null,

    @NotNull
    @Column(name = "dias")
    var dias: Int = 0,

    @NotNull
    @Column(name = "cantidad")
    var cantidad: Int = 0

) : Auditable()

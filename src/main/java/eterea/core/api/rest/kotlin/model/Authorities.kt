package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["cli_internet_id"])])
data class Authorities(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "cli_internet_id")
    var clienteId: Long? = null,

    var authority: String? = null

)

package br.assismoraes.apimock.entities

import br.assismoraes.apimock.domain.Scenario
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "scenarios")
class ScenarioEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "method")
    var method: String,

    @Column(name = "path")
    var path: String,
)

fun ScenarioEntity.toDomain() = Scenario(
        id  = this.id,
        name = this.name!!,
        method = this.method,
        path = this.path
)

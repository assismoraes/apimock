package br.assismoraes.apimock.domain

import br.assismoraes.apimock.entities.ScenarioEntity

class Scenario(
    val id: Long? = null,
    val name: String,
    val method: String,
    val path: String,
) {

    fun toEntity() = ScenarioEntity(
            id = this.id,
            name = this.name,
            method = this.method,
            path = this.path,
    )
}
package br.assismoraes.apimock.domain

import br.assismoraes.apimock.entities.ScenarioEntity

class Scenario(
    val id: Long? = null,
    val name: String = ""
) {

    fun toEntity() = ScenarioEntity(
        name = this.name
    )
}
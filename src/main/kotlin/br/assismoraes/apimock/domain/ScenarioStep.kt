package br.assismoraes.apimock.domain

import br.assismoraes.apimock.entities.ScenarioStepEntity

data class ScenarioStep(
        var id: Long? = null,
        var scenario: Scenario,
        var requestBody: String,
        var responseBody: String,
        var responseStatus: Int,
        var requestHeaders: String,
        var responseHeaders: String,
        var sequence: Int,
        var status: String,
) {
    fun toEntity() = ScenarioStepEntity(
            id = this.id,
            scenario = this.scenario.toEntity(),
            requestBody = this.requestBody,
            responseBody = this.responseBody,
            responseStatus = this.responseStatus,
            requestHeaders = this.requestHeaders,
            responseHeaders = this.responseHeaders,
            sequence = this.sequence,
            status = this.status
    )
}

fun List<ScenarioStep>.toEntity() = this.map { it.toEntity() }
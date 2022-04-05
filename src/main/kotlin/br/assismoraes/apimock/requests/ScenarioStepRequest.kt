package br.assismoraes.apimock.requests

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.domain.ScenarioStep
import br.assismoraes.apimock.enums.StepStatus
import com.fasterxml.jackson.annotation.JsonProperty

data class ScenariosStepsRequest(
        val steps: List<ScenarioStepRequest>
) {}

data class ScenarioStepRequest(
        @JsonProperty("id")
        val id: Long? = null,

        @JsonProperty("request_body")
        val requestBody: String,

        @JsonProperty("response_body")
        val responseBody: String,

        @JsonProperty("response_status")
        val responseStatus: Int,

        @JsonProperty("request_headers")
        val requestHeaders: String,

        @JsonProperty("response_headers")
        val responseHeaders: String,

        @JsonProperty("sequence")
        val sequence: Int,

        @JsonProperty("sleep_time")
        val sleepTime: Long

        ) {}

fun List<ScenarioStepRequest>.toDomain(scenario: Scenario) = this.map { it.toDomain(scenario) }

fun ScenarioStepRequest.toDomain(scenario: Scenario) = ScenarioStep(
        id = this.id,
        scenario = scenario,
        requestBody = this.requestBody,
        responseBody = this.responseBody,
        responseStatus = this.responseStatus,
        requestHeaders = this.requestHeaders,
        responseHeaders = this.responseHeaders,
        sequence = this.sequence,
        status = StepStatus.IDLE.name,
        sleepTime = this.sleepTime
)
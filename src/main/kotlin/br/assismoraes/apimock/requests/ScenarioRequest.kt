package br.assismoraes.apimock.requests

import br.assismoraes.apimock.domain.Scenario
import com.fasterxml.jackson.annotation.JsonProperty

data class ScenarioRequest(
    @JsonProperty("name")
    val name: String
) {

    fun toDomain() = Scenario(
        name = this.name
    )

}

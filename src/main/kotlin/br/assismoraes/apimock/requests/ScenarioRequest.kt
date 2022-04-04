package br.assismoraes.apimock.requests

import br.assismoraes.apimock.domain.Scenario
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ScenarioRequest(
    @field:NotNull
    @field:Size(min = 5, max = 50)
    @JsonProperty("name")
    val name: String,

    @JsonProperty("method")
    val method: String,

    @JsonProperty("path")
    val path: String

) {

    fun toDomain() = Scenario(
            name = this.name,
            method = this.method,
            path = this.path
    )

}

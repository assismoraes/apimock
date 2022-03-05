package br.assismoraes.apimock.controllers

import br.assismoraes.apimock.requests.ScenarioRequest
import br.assismoraes.apimock.services.ScenarioService
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.Scenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [ScenarioController.SCENARIOS_PATH])
class ScenarioController {

    @Autowired
    private lateinit var scenarioService: ScenarioService

    private lateinit var mockService: WireMockServer

    companion object {
        const val SCENARIOS_PATH = "scenarios"
    }

    @PostMapping
    fun create(
        @RequestBody scenarioRequest: ScenarioRequest
    ): ResponseEntity<Any> {
        val scenario = scenarioService.save(scenarioRequest.toDomain())

        try {
            mockService.stop()
        }
        catch (ex: Exception) {
        }
        mock()
        return ResponseEntity.ok(scenario)
    }

    private fun mock() {
        mockService = WireMockServer(8899)
        mockService.start()
        mockService.stubFor(
            WireMock.get(WireMock.anyUrl()).inScenario("SCENARIO1")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("FINISHED")
                .willReturn(
                    WireMock.aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(500)
                        .withBody("{\n" +
                                "        \"message\": \"ERROR\"\n" +
                                "    }")
                )
        )

        mockService.stubFor(
            WireMock.get(WireMock.anyUrl()).inScenario("SCENARIO1")
                .whenScenarioStateIs("FINISHED")
                .willReturn(
                    WireMock.aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\n" +
                                "        \"message\": \"SUCCESS\"\n" +
                                "    }")
                )
        )
    }
}
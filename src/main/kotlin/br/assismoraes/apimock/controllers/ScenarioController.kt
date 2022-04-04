package br.assismoraes.apimock.controllers

import br.assismoraes.apimock.requests.ScenarioRequest
import br.assismoraes.apimock.requests.ScenarioStepRequest
import br.assismoraes.apimock.requests.toDomain
import br.assismoraes.apimock.services.ScenarioService
import br.assismoraes.apimock.services.ScenarioStepService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = [ScenarioController.SCENARIOS_PATH])
class ScenarioController {

    @Autowired
    private lateinit var scenarioService: ScenarioService

    @Autowired
    private lateinit var scenarioStepService: ScenarioStepService

    companion object {
        const val SCENARIOS_PATH = "scenarios"
    }

    @PostMapping
    fun create(@Valid @RequestBody request: ScenarioRequest): ResponseEntity<Any> {
        val scenario = scenarioService.save(request.toDomain())
        return ResponseEntity.ok(scenario)
    }

    @PostMapping("{scenarioId}/steps")
    fun saveSteps(
            @PathVariable("scenarioId") scenarioId: Long,
            @Valid @RequestBody request: List<ScenarioStepRequest>
    ): ResponseEntity<Any> {
        val scenario = scenarioService.findById(scenarioId)
        scenarioStepService.saveSteps(request.toDomain(scenario))
        return ResponseEntity.ok("OK")
    }

}
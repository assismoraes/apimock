package br.assismoraes.apimock.services

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.domain.ScenarioStep
import br.assismoraes.apimock.enums.StepStatus
import br.assismoraes.apimock.repositories.ScenarioStepRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ScenarioStepService {

    @Autowired
    private lateinit var scenarioStepRepository: ScenarioStepRepository

    fun saveSteps(steps: List<ScenarioStep>) = scenarioStepRepository.saveAll(steps)

    fun findByScenario(scenario: Scenario) = scenarioStepRepository.findByScenario(scenario)

    fun save(step: ScenarioStep) = scenarioStepRepository.save(step)

    fun getResponse(scenario: Scenario): ResponseEntity<Any> {
        val steps = findByScenario(scenario)

        steps.forEach {
            if(it.status == StepStatus.IDLE.name) {
                it.status = StepStatus.EXECUTED.name
                save(it)
                return ResponseEntity.status(it.responseStatus).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(it.responseBody)
            }
        }

        return ResponseEntity.badRequest().body("Request was not defined")
    }

}

package br.assismoraes.apimock.repositories

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.domain.ScenarioStep
import br.assismoraes.apimock.domain.toEntity
import br.assismoraes.apimock.entities.ScenarioEntity
import br.assismoraes.apimock.entities.ScenarioStepEntity
import br.assismoraes.apimock.entities.toDomain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
class ScenarioStepRepository(private val scenarioStepRepositoryJpa: ScenarioStepRepositoryJpa) {

    fun save(step: ScenarioStep) = scenarioStepRepositoryJpa.save(step.toEntity()).toDomain()

    fun saveAll(steps: List<ScenarioStep>) = scenarioStepRepositoryJpa.saveAll(steps.toEntity())

    fun findByScenario(scenario: Scenario) = scenarioStepRepositoryJpa.findByScenarioOrderBySequenceAsc(scenario.toEntity()).toDomain()

}

interface ScenarioStepRepositoryJpa: CrudRepository<ScenarioStepEntity, Long> {

    override fun findAll(): List<ScenarioStepEntity>

    fun findByScenarioOrderBySequenceAsc(scenario: ScenarioEntity): List<ScenarioStepEntity>

}
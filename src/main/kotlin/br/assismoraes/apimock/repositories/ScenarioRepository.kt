package br.assismoraes.apimock.repositories

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.entities.ScenarioEntity
import br.assismoraes.apimock.entities.toDomain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
class ScenarioRepository(private val scenarioRepositoryJpa: ScenarioRepositoryJpa) {

    fun save(scenario: Scenario) = scenarioRepositoryJpa.save(scenario.toEntity()).toDomain()

}

interface ScenarioRepositoryJpa: CrudRepository<ScenarioEntity, Long>

package br.assismoraes.apimock.repositories

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.entities.ScenarioEntity
import br.assismoraes.apimock.entities.toDomain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ScenarioRepository(private val scenarioRepositoryJpa: ScenarioRepositoryJpa) {

    fun save(scenario: Scenario) = scenarioRepositoryJpa.save(scenario.toEntity()).toDomain()
    fun findAll(): List<Scenario> = scenarioRepositoryJpa.findAll().toListDomain()

}

interface ScenarioRepositoryJpa: CrudRepository<ScenarioEntity, Long> {

    override fun findAll(): List<ScenarioEntity>

}

private fun List<ScenarioEntity>.toListDomain(): List<Scenario> {
    val list = ArrayList<Scenario>()
    this.forEach {
        list.add(it.toDomain())
    }
    return list
}
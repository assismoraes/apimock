package br.assismoraes.apimock.repositories

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.entities.ScenarioEntity
import br.assismoraes.apimock.entities.toDomain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class ScenarioRepository(private val scenarioRepositoryJpa: ScenarioRepositoryJpa) {

    fun save(scenario: Scenario) = scenarioRepositoryJpa.save(scenario.toEntity()).toDomain()

    fun findAll(): List<Scenario> = scenarioRepositoryJpa.findAll().toListDomain()

    fun findById(id: Long): Scenario = scenarioRepositoryJpa.findById(id).map { it.toDomain() }.orElse(null)

    fun findByMethodAndPath(method: String, path: String): Scenario = scenarioRepositoryJpa.findByMethodAndPath(method, path).map { it.toDomain() }.orElse(null)

}

interface ScenarioRepositoryJpa: CrudRepository<ScenarioEntity, Long> {

    override fun findAll(): List<ScenarioEntity>

    fun findByMethodAndPath(method: String, path: String): Optional<ScenarioEntity>

}

private fun List<ScenarioEntity>.toListDomain(): List<Scenario> {
    val list = ArrayList<Scenario>()
    this.forEach {
        list.add(it.toDomain())
    }
    return list
}
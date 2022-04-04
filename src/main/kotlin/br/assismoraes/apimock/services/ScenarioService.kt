package br.assismoraes.apimock.services

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.repositories.ScenarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScenarioService {

    @Autowired
    private lateinit var scenarioRepository: ScenarioRepository

    fun save(scenario: Scenario) = scenarioRepository.save(scenario)

    fun findAll(): List<Scenario> = scenarioRepository.findAll()

    fun findById(id: Long) = scenarioRepository.findById(id)

    fun findByMethodAndPath(method: String, path: String) = scenarioRepository.findByMethodAndPath(method, path)

}

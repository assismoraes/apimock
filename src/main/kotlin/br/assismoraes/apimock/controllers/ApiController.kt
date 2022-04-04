package br.assismoraes.apimock.controllers

import br.assismoraes.apimock.enums.StepStatus
import br.assismoraes.apimock.services.ScenarioService
import br.assismoraes.apimock.services.ScenarioStepService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = [ ApiController.API_PATH ])
class ApiController {

    @Autowired
    private lateinit var scenarioService: ScenarioService

    @Autowired
    private lateinit var scenarioStepService: ScenarioStepService

    companion object {
        const val API_PATH = "api"
    }

    @PostMapping("{*path}")
    fun postPath(@PathVariable("path") path: String): ResponseEntity<Any> {
        val scenario = scenarioService.findByMethodAndPath(method = HttpMethod.POST.name, path = path)
        return scenarioStepService.getResponse(scenario)
    }

    @GetMapping("{*path}")
    fun getPath(@PathVariable("path") path: String): ResponseEntity<Any> {
        val scenario = scenarioService.findByMethodAndPath(method = HttpMethod.GET.name, path = path)
        return scenarioStepService.getResponse(scenario)
    }

}
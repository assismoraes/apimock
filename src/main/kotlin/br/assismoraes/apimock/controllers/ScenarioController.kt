package br.assismoraes.apimock.controllers

import br.assismoraes.apimock.domain.Scenario
import br.assismoraes.apimock.requests.ScenarioRequest
import br.assismoraes.apimock.services.ScenarioService
import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping(path = [ScenarioController.SCENARIOS_PATH])
class ScenarioController {

    @Autowired
    private lateinit var scenarioService: ScenarioService

    private lateinit var mockService: WireMockServer

    companion object {
        const val SCENARIOS_PATH = "scenarios"
        private const val SCENARIOS_BASE_BASE_DIR = "scenarios"
        private const val SCENARIOS_FORM = "$SCENARIOS_BASE_BASE_DIR/form"
        private const val SCENARIOS_LIST = "$SCENARIOS_BASE_BASE_DIR/list"
    }

    @GetMapping("new")
    fun new(model: Model): ModelAndView {
        val mav = ModelAndView(SCENARIOS_FORM)
        mav.addObject("scenario", model.getAttribute("scenario") ?: Scenario())
        return mav
    }

    @PostMapping
    fun create(@Valid scenario: ScenarioRequest, bindingResult: BindingResult, redirectAttributes: RedirectAttributes): String {
        if(bindingResult.hasErrors()) {
            val e = bindingResult.allErrors
            redirectAttributes.addFlashAttribute("scenario", scenario)
            redirectAttributes.addFlashAttribute("errors", bindingResult.allErrors)
            return "redirect:$SCENARIOS_PATH/new"
        }
        val scenario = scenarioService.save(scenario.toDomain())
        return "redirect:$SCENARIOS_PATH"
    }

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("scenarios", scenarioService.findAll())
        return "$SCENARIOS_LIST"
    }

    @GetMapping("{id}")
    fun edit(@PathVariable("id") id: Long): String {
        return id.toString()
    }

//    private fun mock() {
//        mockService = WireMockServer(8899)
//        mockService.start()
//        mockService.stubFor(
//            WireMock.get(WireMock.anyUrl()).inScenario("SCENARIO1")
//                .willSetStateTo("FINISHED")
//                .willReturn(
//                    WireMock.aResponse()
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withStatus(500)
//                        .withBody("{\n" +
//                                "        \"message\": \"ERROR\"\n" +
//                                "    }")
//                )
//        )
//
//        mockService.stubFor(
//            WireMock.get(WireMock.anyUrl()).inScenario("SCENARIO1")
//                .whenScenarioStateIs("FINISHED")
//                .willReturn(
//                    WireMock.aResponse()
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\n" +
//                                "        \"message\": \"SUCCESS\"\n" +
//                                "    }")
//                )
//        )
//    }
}
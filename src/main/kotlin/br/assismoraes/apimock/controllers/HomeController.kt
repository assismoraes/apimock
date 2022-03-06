package br.assismoraes.apimock.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }

    @ModelAttribute("contact")
    fun contactModel(): Contact {
        return Contact(
            name = "Fulano de Tal",
            email = "fulano@email.com"
        )
    }
}

data class Contact(
    val name: String,
    val email: String
)
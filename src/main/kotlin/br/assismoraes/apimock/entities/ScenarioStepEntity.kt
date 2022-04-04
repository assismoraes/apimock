package br.assismoraes.apimock.entities

import br.assismoraes.apimock.domain.ScenarioStep
import javax.persistence.*

@Entity
@Table(name = "scenario_steps")
class ScenarioStepEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "scenario_id")
        var scenario: ScenarioEntity,

        @Column(name = "request_body")
        var requestBody: String,

        @Column(name = "response_body")
        var responseBody: String,

        @Column(name = "response_status")
        var responseStatus: Int,

        @Column(name = "request_headers")
        var requestHeaders: String,

        @Column(name = "response_headers")
        var responseHeaders: String,

        @Column(name = "sequence")
        var sequence: Int,

        @Column(name = "status")
        var status: String,

        ) {
        fun toDomain() = ScenarioStep(
                id = this.id,
                scenario = this.scenario.toDomain(),
                requestBody = this.requestBody,
                responseBody = this.responseBody,
                responseStatus = this.responseStatus,
                requestHeaders = this.requestHeaders,
                responseHeaders = this.responseHeaders,
                sequence = this.sequence,
                status = this.status
        )

}

fun List<ScenarioStepEntity>.toDomain() = this.map { it.toDomain() }
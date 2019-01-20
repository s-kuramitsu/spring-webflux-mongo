package com.example.validation

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class RequestHandler(private val validator: Validator) {
    fun <BODY> withValidBody(
            block: (Flux<BODY>) -> Mono<ServerResponse>,
            request: ServerRequest, bodyClass: Class<BODY>): Mono<ServerResponse> {

        return request
                .bodyToMono(bodyClass)
                .flatMap { body ->
                    val violations = validator.validate(body)
                    if (violations.isEmpty())
                        block.invoke(Flux.just(body))
                    else
                        throw Exception()
                }
    }
}

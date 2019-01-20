package com.example.api

import com.example.entity.Content
import com.example.service.ContentService
import com.example.validation.RequestHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.net.URI

@Component
class ContentHandler {
    @Autowired
    lateinit var contentService: ContentService

    @Autowired
    lateinit var requestHandler: RequestHandler

    fun findOne(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        return ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(contentService.findOne(id), Content::class.java)
    }

    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        return ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(contentService.findAll(), Content::class.java)
    }

    fun create(request: ServerRequest): Mono<ServerResponse> {
        return requestHandler.withValidBody({input ->
            contentService.create(input).single()
                    .flatMap { result -> ServerResponse.created(URI.create("http://localhost:8080/contents/${result.id}"))
                                                        .contentType(APPLICATION_JSON_UTF8)
                                                        .body(result.toMono()) }
        }, request, Content::class.java)
    }
}

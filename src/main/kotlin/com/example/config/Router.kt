package com.example.config

import com.example.api.ContentHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(private val contentHandler: ContentHandler) {
    @Bean
    fun apiRouter() = router {
        accept(MediaType.APPLICATION_JSON_UTF8).nest {
            GET("/contents", contentHandler::findAll)
            GET("/contents/{id}", contentHandler::findOne)
            POST("/contents", contentHandler::create)
        }
    }
}
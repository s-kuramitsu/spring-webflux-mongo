package com.example.service

import com.example.entity.Content
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ContentService {
    fun create(content: Flux<Content>): Flux<Content>
    fun findOne(id: String): Mono<Content>
    fun findAll(): Flux<Content>
}
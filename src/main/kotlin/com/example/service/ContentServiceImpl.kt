package com.example.service

import com.example.entity.Content
import com.example.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class ContentServiceImpl: ContentService {
    @Autowired
    lateinit var contentRepository: ContentRepository

    @Override
    override fun create(content: Flux<Content>):Flux<Content> {
        return contentRepository.insert(content)
    }

    @Override
    override fun findOne(id: String): Mono<Content> {
        return contentRepository.findById(id)
    }

    @Override
    override fun findAll(): Flux<Content> {
        return contentRepository.findAll()
    }
}
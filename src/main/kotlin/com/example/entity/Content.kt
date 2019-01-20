package com.example.entity

import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Document(collection = "content")
data class Content (
        @get:NotEmpty
        @get:Size(max = 255)
        val fileName : String,
        @get:NotEmpty
        @get:Size(max = 255)
        val filePath : String
): BaseEntity()

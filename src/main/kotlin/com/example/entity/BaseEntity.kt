package com.example.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.*
import org.springframework.data.domain.Persistable
import java.util.*

@Suppress("unused")
abstract class BaseEntity (
        var id: ObjectId? = null,
        var createdBy: String = "",
        var createdDate: Date? = null,
        var updatedBy: String = "",
        var updatedDate: Date? = null,
        var version: Long = 0,
        var delete: Boolean = false
) {
    init {
        val date = Date()
        this.createdDate = date
        this.updatedDate = date
    }
}

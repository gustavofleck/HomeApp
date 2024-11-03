package com.example.homeapp.annotations.domain.model

internal data class AnnotationItem(
    val id: String,
    val text: String,
    val favorite: Boolean,
    val created: String,
    val updated: String
)

package com.example.homeapp.notes.domain.model

internal data class Note(
    val id: String,
    val text: String,
    val favorite: Boolean,
    val created: String,
    val updated: String
)
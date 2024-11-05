package com.example.homeapp.notes.data.model

internal data class NoteRequest(
    val fields: NoteFields
)

internal data class NoteFields(
    val favorite: Favorite,
    val text: NoteText
)

internal data class Favorite(
    val booleanValue: Boolean
)

internal data class NoteText(
    val stringValue: String
)
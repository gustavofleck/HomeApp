package com.example.homeapp.features.notes.domain.repository

import com.example.homeapp.features.notes.domain.model.Note

internal interface NotesRepository {

    suspend fun notes(): List<Note>

    suspend fun addNote(text: String, favorite: Boolean): Result<Unit>
}
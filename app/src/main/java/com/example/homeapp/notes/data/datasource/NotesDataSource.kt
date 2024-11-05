package com.example.homeapp.notes.data.datasource

import com.example.homeapp.notes.data.model.NoteRequest
import com.example.homeapp.notes.data.model.toDomain
import com.example.homeapp.notes.data.service.NotesApi
import com.example.homeapp.notes.domain.model.Note

internal class NotesDataSource(
    private val api: NotesApi
) {

    suspend fun notes(): List<Note> {
        return api.notes().documents?.map { annotationResponse ->
            annotationResponse.toDomain()
        }.orEmpty()
    }

    suspend fun addNote(note: NoteRequest): Result<Unit> {
        return runCatching {
            api.addNote(note)
        }
    }

}
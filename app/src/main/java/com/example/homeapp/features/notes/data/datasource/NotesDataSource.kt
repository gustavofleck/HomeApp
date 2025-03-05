package com.example.homeapp.features.notes.data.datasource

import com.example.homeapp.features.notes.data.model.NoteRequest
import com.example.homeapp.features.notes.data.model.toDomain
import com.example.homeapp.features.notes.data.service.NotesApi
import com.example.homeapp.features.notes.domain.model.Note

internal class NotesDataSource(
    private val api: NotesApi
) {

    suspend fun notes(): List<Note> {
        return api.notes().documents?.map { notesResponse ->
            notesResponse.toDomain()
        }.orEmpty()
    }

    suspend fun addNote(note: NoteRequest): Result<Unit> {
        return runCatching {
            api.addNote(note)
        }
    }

}
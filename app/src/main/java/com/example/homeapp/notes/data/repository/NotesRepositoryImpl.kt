package com.example.homeapp.notes.data.repository

import com.example.homeapp.notes.data.datasource.NotesDataSource
import com.example.homeapp.notes.data.model.Favorite
import com.example.homeapp.notes.data.model.NoteFields
import com.example.homeapp.notes.data.model.NoteRequest
import com.example.homeapp.notes.data.model.NoteText
import com.example.homeapp.notes.domain.repository.NotesRepository

internal class NotesRepositoryImpl(
    private val dataSource: NotesDataSource
): NotesRepository {

    override suspend fun notes() = dataSource.notes()

    override suspend fun addNote(text: String, favorite: Boolean): Result<Unit> {
        val noteRequest = NoteRequest(
            fields = NoteFields(
                favorite = Favorite(favorite),
                text = NoteText(text)
            )
        )
        return dataSource.addNote(noteRequest)
    }

}
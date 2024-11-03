package com.example.homeapp.notes.domain.repository

import com.example.homeapp.notes.domain.model.Note

internal interface NotesRepository {

    suspend fun notes(): List<Note>
}
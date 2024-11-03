package com.example.homeapp.annotations.domain.repository

import com.example.homeapp.annotations.domain.model.Note

internal interface NotesRepository {

    suspend fun notes(): List<Note>
}
package com.example.homeapp.features.notes.domain.usecase

import com.example.homeapp.features.notes.domain.model.Note
import com.example.homeapp.features.notes.domain.repository.NotesRepository

internal class ListNotesUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(): Result<List<Note>> {
        runCatching {
            val notes = repository.notes()
            val favoriteNotes = notes.filter { it.favorite }
            val nonFavoriteNotes = notes.filter { it.favorite.not() }
            return Result.success(favoriteNotes + nonFavoriteNotes)
        }.getOrElse { exception ->
            return Result.failure(exception)
        }
    }

}
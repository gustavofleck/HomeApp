package com.example.homeapp.features.notes.domain.usecase

import com.example.homeapp.features.notes.domain.repository.NotesRepository

internal class AddNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(text: String, favorite: Boolean) =
        repository.addNote(text, favorite)

}
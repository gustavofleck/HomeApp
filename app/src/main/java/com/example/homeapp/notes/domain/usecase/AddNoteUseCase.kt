package com.example.homeapp.notes.domain.usecase

import com.example.homeapp.notes.domain.repository.NotesRepository

internal class AddNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(text: String, favorite: Boolean) =
        repository.addNote(text, favorite)

}
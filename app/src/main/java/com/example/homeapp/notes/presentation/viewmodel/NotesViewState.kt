package com.example.homeapp.notes.presentation.viewmodel

import com.example.homeapp.notes.domain.model.Note

internal sealed class NotesViewState {

    data object Loading : NotesViewState()
    data object AddNote : NotesViewState()
    data object Empty : NotesViewState()
    data class Error(val message: String) : NotesViewState()
    data class Loaded(val notes: List<Note>) : NotesViewState()

}

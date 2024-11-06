package com.example.homeapp.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.notes.domain.model.Note
import com.example.homeapp.notes.domain.usecase.AddNoteUseCase
import com.example.homeapp.notes.domain.usecase.ListNotesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class NotesViewModel(
    private val listUseCase: ListNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow<NotesViewState>(NotesViewState.Loading)
    val state: StateFlow<NotesViewState> = _state

    fun onRetrieveNotes() {
        viewModelScope.launch(dispatcher) {
            _state.value = NotesViewState.Loading
            listUseCase()
                .onFailure { handleStateSet(NotesViewState.Error(it.message.orEmpty())) }
                .onSuccess { handleStateSet(setupLoadedState(it)) }
        }
    }

    private fun setupLoadedState(notes: List<Note>) = if (notes.isEmpty()) {
        NotesViewState.Empty
    } else NotesViewState.Loaded(notes)

    fun onAddNoteClicked() {
        _state.value = NotesViewState.AddNote
    }

    fun onAddNoteConfirm(text: String, favorite: Boolean) {
        viewModelScope.launch(dispatcher) {
            addNoteUseCase(text, favorite)
                .onFailure { handleStateSet(NotesViewState.Error(it.message.orEmpty())) }
                .onSuccess { onRetrieveNotes() }
        }
    }

    private suspend fun handleStateSet(state: NotesViewState) =
        withContext(Dispatchers.Main) {
            _state.value = state
        }
}
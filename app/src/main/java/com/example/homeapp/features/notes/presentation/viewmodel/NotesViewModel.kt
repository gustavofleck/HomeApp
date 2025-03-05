package com.example.homeapp.features.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.features.notes.domain.model.Note
import com.example.homeapp.features.notes.domain.usecase.AddNoteUseCase
import com.example.homeapp.features.notes.domain.usecase.ListNotesUseCase
import com.example.homeapp.features.notes.presentation.viewmodel.NotesViewState.Empty
import com.example.homeapp.features.notes.presentation.viewmodel.NotesViewState.Error
import com.example.homeapp.features.notes.presentation.viewmodel.NotesViewState.Loaded
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
                .onFailure { handleStateSet(Error(it.message.orEmpty())) }
                .onSuccess { handleStateSet(setupLoadedState(it)) }
        }
    }

    private fun setupLoadedState(notes: List<Note>) =
        if (notes.isEmpty()) Empty else Loaded(notes)

    fun onAddNoteClicked() {
        _state.value = NotesViewState.AddNote
    }

    fun onAddNoteConfirm(text: String, favorite: Boolean) {
        viewModelScope.launch(dispatcher) {
            addNoteUseCase(text, favorite)
                .onFailure { handleStateSet(Error(it.message.orEmpty())) }
                .onSuccess { onRetrieveNotes() }
        }
    }

    private suspend fun handleStateSet(state: NotesViewState) =
        withContext(Dispatchers.Main) {
            _state.value = state
        }
}
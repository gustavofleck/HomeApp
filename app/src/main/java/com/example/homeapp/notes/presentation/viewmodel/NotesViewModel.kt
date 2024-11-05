package com.example.homeapp.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                .onFailure {
                    withContext(Dispatchers.Main) {
                        _state.value = NotesViewState.Error(it.message.orEmpty())
                    }
                    it.printStackTrace()
                }.onSuccess {
                    withContext(Dispatchers.Main) {
                        _state.value = NotesViewState.Loaded(it)
                    }
                }
        }
    }

    fun onAddNoteClicked() {
        _state.value = NotesViewState.AddNote
    }

    fun onAddNoteConfirm(text: String, favorite: Boolean) {
        viewModelScope.launch(dispatcher) {
            addNoteUseCase(text, favorite)
                .onFailure {
                    withContext(Dispatchers.Main) {
                        _state.value = NotesViewState.Error(it.message.orEmpty())
                    }
                    it.printStackTrace()
                }
                .onSuccess {
                    onRetrieveNotes()
                }
        }
    }
}
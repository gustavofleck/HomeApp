package com.example.homeapp.notes.presentation.viewmodel

import app.cash.turbine.test
import com.example.homeapp.common.rules.MainDispatcherRule
import com.example.homeapp.notes.domain.usecase.AddNoteUseCase
import com.example.homeapp.notes.domain.usecase.ListNotesUseCase
import com.example.homeapp.stubs.orderedNoteList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class NotesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val listNotesUseCase = mockk<ListNotesUseCase>(relaxed = true)
    private val addNoteUseCase = mockk<AddNoteUseCase>(relaxed = true)
    private lateinit var viewModel: NotesViewModel

    @Before
    fun setUp() {
        coEvery { listNotesUseCase.invoke() } returns Result.success(orderedNoteList)

        viewModel =
            NotesViewModel(listNotesUseCase, addNoteUseCase, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `onRetrieveNotes Should send Loaded state with list notes use case on success`() = runTest {
        viewModel.state.test {
            viewModel.onRetrieveNotes()

            assertEquals(NotesViewState.Loading, awaitItem())
            assertEquals(NotesViewState.Loaded(orderedNoteList), awaitItem())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `onRetrieveNotes Should send Error state with list notes use case on failure`() = runTest {
        val expectedMessage = "expectedMessage"
        coEvery { listNotesUseCase.invoke() } returns Result.failure(Exception(expectedMessage))

        viewModel.state.test {
            viewModel.onRetrieveNotes()

            assertEquals(NotesViewState.Loading, awaitItem())
            assertEquals(NotesViewState.Error(expectedMessage), awaitItem())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `onAddNoteClicked Should send AddNote state`() = runTest {
        viewModel.state.test {
            viewModel.onAddNoteClicked()

            assertEquals(NotesViewState.Loading, awaitItem())
            assertEquals(NotesViewState.AddNote, awaitItem())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `onAddNoteConfirm Should send Loaded state with add note use case on success`() = runTest {
        val text = "text"
        val favorite = true
        coEvery { addNoteUseCase(text, favorite) } returns Result.success(Unit)

        viewModel.state.test {
            viewModel.onAddNoteConfirm(text, favorite)

            assertEquals(NotesViewState.Loading, awaitItem())
            assertEquals(NotesViewState.Loaded(orderedNoteList), awaitItem())
            ensureAllEventsConsumed()
        }

    }

    @Test
    fun `onAddNoteConfirm Should send Error state with add note use case on failure`() = runTest {
        val text = "text"
        val favorite = true
        val expectedMessage = "expectedMessage"
        coEvery { listNotesUseCase.invoke() } returns Result.success(orderedNoteList)
        coEvery {
            addNoteUseCase(text, favorite)
        } returns Result.failure(Exception(expectedMessage))

        viewModel.state.test {
            viewModel.onAddNoteConfirm(text, favorite)

            assertEquals(NotesViewState.Loading, awaitItem())
            assertEquals(NotesViewState.Error(expectedMessage), awaitItem())
            ensureAllEventsConsumed()
        }
    }
}
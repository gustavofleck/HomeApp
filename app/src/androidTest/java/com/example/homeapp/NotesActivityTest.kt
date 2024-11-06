package com.example.homeapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.homeapp.notes.domain.model.Note
import com.example.homeapp.notes.presentation.view.NotesActivity
import com.example.homeapp.notes.presentation.viewmodel.NotesViewModel
import com.example.homeapp.notes.presentation.viewmodel.NotesViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.StateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal class NotesActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<NotesActivity>()

    private val viewState = mockk<StateFlow<NotesViewState>>(relaxed = true)
    private val viewModel = mockk<NotesViewModel>(relaxed = true)

    private val koinModule = module {
        viewModel {
            viewModel
        }
    }

    @Before
    fun setUp() {
        every { viewModel.state } returns viewState
        loadKoinModules(koinModule)
    }

    @Test
    fun notesScreen_isDisplayed() {
        every { viewState.value } returns NotesViewState.Loading

        composeTestRule.onNodeWithText("Anotações").assertIsDisplayed()
    }

    @Test
    fun noteScreen_errorIsDisplayed_whenErrorOccurs() {
        every { viewState.value } returns NotesViewState.Error("mensagem")

        composeTestRule.onNodeWithText("Ocorreu um erro inesperado, tente novamente")
            .assertIsDisplayed()
    }

    @Test
    fun noteScreen_notesAreDisplayed_whenLoaded() {
        every { viewState.value } returns NotesViewState.Loaded(setupNotes())

        with(composeTestRule) {
            onNodeWithText("text1").assertIsDisplayed()
            onNodeWithText("text2").assertIsDisplayed()
        }
    }

    @Test
    fun noteScreen_addNoteClick_displaysAddNoteState() {
        every { viewState.value } returns NotesViewState.AddNote

        with(composeTestRule) {
            onNodeWithText("Favoritar").assertIsDisplayed()
            onNodeWithText("Escreva aqui sua anotação").assertIsDisplayed()
        }
    }

    private fun setupNotes() = listOf(
        Note(
            id = "id1",
            text = "text1",
            favorite = true,
            created = "timestamp",
            updated = "timestamp"
        ),
        Note(
            id = "id2",
            text = "text2",
            favorite = false,
            created = "timestamp",
            updated = "timestamp"
        )
    )

}
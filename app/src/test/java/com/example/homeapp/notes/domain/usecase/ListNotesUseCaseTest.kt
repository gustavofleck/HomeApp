package com.example.homeapp.notes.domain.usecase

import com.example.homeapp.notes.domain.model.Note
import com.example.homeapp.notes.domain.repository.NotesRepository
import com.example.homeapp.stubs.noteList
import com.example.homeapp.stubs.orderedNoteList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class ListNotesUseCaseTest {

    private val repository = mockk<NotesRepository>(relaxed = true)
    private val useCase = ListNotesUseCase(repository)

    @Test
    fun `invoke Should return ordered annotations`() = runBlocking {
        val expectedResult = Result.success(orderedNoteList)
        coEvery { repository.notes() } returns noteList

        val result = useCase()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke Should throw an Exception when repository throws an Exception`() = runBlocking {
        val expectedError = Exception()
        val expectedResult = Result.failure<List<Note>>(expectedError)
        coEvery { repository.notes() } throws expectedError

        val result = useCase()

        assertEquals(expectedResult, result)
    }

}
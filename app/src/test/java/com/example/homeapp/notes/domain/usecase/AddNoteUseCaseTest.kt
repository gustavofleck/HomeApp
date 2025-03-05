package com.example.homeapp.notes.domain.usecase

import com.example.homeapp.features.notes.domain.repository.NotesRepository
import com.example.homeapp.features.notes.domain.usecase.AddNoteUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AddNoteUseCaseTest {

    private val text = "text"
    private val favorite = true
    private val repository = mockk<NotesRepository>()
    private val useCase = AddNoteUseCase(repository)

    @Test
    fun `invoke Should return a Result Success When repository returns Result Success`() = runBlocking {
        val expectedResult = Result.success(Unit)
        coEvery { repository.addNote(text, favorite) } returns expectedResult

        val result = useCase.invoke(text, favorite)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke Should return a Result Failure When repository returns Result Failure`() = runBlocking {
        val expectedResult = Result.failure<Unit>(Exception())
        coEvery { repository.addNote(text, favorite) } returns expectedResult

        val result = useCase.invoke(text, favorite)

        assertEquals(expectedResult, result)
    }
}
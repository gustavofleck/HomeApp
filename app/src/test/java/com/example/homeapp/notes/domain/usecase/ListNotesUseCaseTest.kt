package com.example.homeapp.notes.domain.usecase

import com.example.homeapp.notes.domain.repository.NotesRepository
import com.example.homeapp.stubs.noteList
import com.example.homeapp.stubs.orderedNoteList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

internal class ListNotesUseCaseTest {

    private val repository = mockk<NotesRepository>()
    private val useCase = ListNotesUseCase(repository)

    @Test
    fun `invoke Should return ordered annotations`() = runBlocking {
        val expectedAnnotations = orderedNoteList
        coEvery { repository.notes() } returns noteList

        val result = useCase()

        assertEquals(expectedAnnotations, result)
    }

    @Test
    fun `invoke Should throw an Exception when repository throws an Exception`() {
        coEvery { repository.notes() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking { useCase() }
        }
    }

}
package com.example.homeapp.notes.data.repository

import com.example.homeapp.features.notes.data.datasource.NotesDataSource
import com.example.homeapp.features.notes.data.model.Favorite
import com.example.homeapp.features.notes.data.model.NoteFields
import com.example.homeapp.features.notes.data.model.NoteRequest
import com.example.homeapp.features.notes.data.model.NoteText
import com.example.homeapp.features.notes.data.repository.NotesRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test

internal class NotesRepositoryImplTest {

    private val dataSource = mockk<NotesDataSource>(relaxed = true)
    private val repository = NotesRepositoryImpl(dataSource)

    @Test
    fun `notes Should call annotations method of dataSource`() = runBlocking {
        repository.notes()

        coVerify {
            dataSource.notes()
        }
    }

    @Test
    fun `notes Should throw an Exception When dataSource throws an Exception`() {
        coEvery { dataSource.notes() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking {
                repository.notes()
            }
        }
    }

    @Test
    fun `addNote Should setup request and call addNote method of dataSource`() = runBlocking {
        val expectedText = "text"
        val expectedFavorite = true
        val expectedRequest = NoteRequest(
            NoteFields(
                Favorite(booleanValue = expectedFavorite),
                NoteText(stringValue = expectedText)
            )
        )
        coEvery { dataSource.addNote(expectedRequest) } returns Result.success(Unit)

        repository.addNote(expectedText, expectedFavorite)

        coVerify {
            dataSource.addNote(expectedRequest)
        }
    }

    @Test
    fun `addNote Should return Result Failure When dataSource returns Result Failure`() =
        runBlocking {
            val expectedText = "text"
            val expectedFavorite = true
            val expectedRequest = NoteRequest(
                NoteFields(
                    Favorite(booleanValue = expectedFavorite),
                    NoteText(stringValue = expectedText)
                )
            )
            coEvery { dataSource.addNote(expectedRequest) } returns Result.failure(Exception())

            val result = repository.addNote(expectedText, expectedFavorite)

            assert(result.isFailure)
        }
}
package com.example.homeapp.notes.data.repository

import com.example.homeapp.notes.data.datasource.NotesDataSource
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
    fun `annotations Should call annotations method of dataSource`() = runBlocking {
        repository.notes()

        coVerify {
            dataSource.notes()
        }
    }

    @Test
    fun `annotation Should throw an Exception When dataSource throws an Exception`() {
        coEvery { dataSource.notes() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking {
                repository.notes()
            }
        }
    }
}
package com.example.homeapp.notes.data.datasource

import com.example.homeapp.notes.data.service.NotesApi
import com.example.homeapp.stubs.noteList
import com.example.homeapp.stubs.noteListResponse
import com.example.homeapp.stubs.nullAnnotationListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

internal class NotesDataSourceTest {

    private val api = mockk<NotesApi>()
    private val dataSource = NotesDataSource(api)

    @Test
    fun `annotations Should return a list of annotations When api returns data with success`() = runBlocking {
        val expectedAnnotations = noteList
        coEvery { api.notes() } returns noteListResponse

        val result = dataSource.notes()

        assertEquals(expectedAnnotations, result)
    }

    @Test
    fun `annotations Should return an empty list When api returns null data`() = runBlocking {
        val expectedAnnotations = emptyList<String>()
        coEvery { api.notes() } returns nullAnnotationListResponse

        val result = dataSource.notes()

        assertEquals(expectedAnnotations, result)
    }

    @Test
    fun `annotations Should return an empty list When api throws an exception`() {
        coEvery { api.notes() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking {
                dataSource.notes()
            }
        }
    }
}
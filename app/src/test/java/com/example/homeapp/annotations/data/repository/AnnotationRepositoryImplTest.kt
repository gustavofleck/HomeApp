package com.example.homeapp.annotations.data.repository

import com.example.homeapp.annotations.data.datasource.AnnotationsDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test

internal class AnnotationRepositoryImplTest {

    private val dataSource = mockk<AnnotationsDataSource>(relaxed = true)
    private val repository = AnnotationRepositoryImpl(dataSource)

    @Test
    fun `annotations Should call annotations method of dataSource`() = runBlocking {
        repository.annotations()

        coVerify {
            dataSource.annotations()
        }
    }

    @Test
    fun `annotation Should throw an Exception When dataSource throws an Exception`() {
        coEvery { dataSource.annotations() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking {
                repository.annotations()
            }
        }
    }
}
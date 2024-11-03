package com.example.homeapp.annotations.domain.usecase

import com.example.homeapp.annotations.domain.repository.AnnotationsRepository
import com.example.homeapp.stubs.annotationList
import com.example.homeapp.stubs.orderedAnnotationList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

internal class ListAnnotationsUseCaseTest {

    private val repository = mockk<AnnotationsRepository>()
    private val useCase = ListAnnotationsUseCase(repository)

    @Test
    fun `invoke Should return ordered annotations`() = runBlocking {
        val expectedAnnotations = orderedAnnotationList
        coEvery { repository.annotations() } returns annotationList

        val result = useCase()

        assertEquals(expectedAnnotations, result)
    }

    @Test
    fun `invoke Should throw an Exception when repository throws an Exception`() {
        coEvery { repository.annotations() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking { useCase() }
        }
    }

}
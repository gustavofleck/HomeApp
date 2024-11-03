package com.example.homeapp.annotations.data.datasource

import com.example.homeapp.annotations.data.service.AnnotationsApi
import com.example.homeapp.stubs.annotationList
import com.example.homeapp.stubs.annotationListResponse
import com.example.homeapp.stubs.nullAnnotationListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

internal class AnnotationsDataSourceTest {

    private val api = mockk<AnnotationsApi>()
    private val dataSource = AnnotationsDataSource(api)

    @Test
    fun `annotations Should return a list of annotations When api returns data with success`() = runBlocking {
        val expectedAnnotations = annotationList
        coEvery { api.annotations() } returns annotationListResponse

        val result = dataSource.annotations()

        assertEquals(expectedAnnotations, result)
    }

    @Test
    fun `annotations Should return an empty list When api returns null data`() = runBlocking {
        val expectedAnnotations = emptyList<String>()
        coEvery { api.annotations() } returns nullAnnotationListResponse

        val result = dataSource.annotations()

        assertEquals(expectedAnnotations, result)
    }

    @Test
    fun `annotations Should return an empty list When api throws an exception`() {
        coEvery { api.annotations() } throws Exception()

        assertThrows(Exception::class.java) {
            runBlocking {
                dataSource.annotations()
            }
        }
    }
}
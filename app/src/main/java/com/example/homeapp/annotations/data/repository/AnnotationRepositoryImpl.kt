package com.example.homeapp.annotations.data.repository

import com.example.homeapp.annotations.data.datasource.AnnotationsDataSource
import com.example.homeapp.annotations.domain.repository.AnnotationsRepository

internal class AnnotationRepositoryImpl(
    private val dataSource: AnnotationsDataSource
): AnnotationsRepository {

    override suspend fun annotations() = dataSource.annotations()

}
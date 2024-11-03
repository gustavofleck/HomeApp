package com.example.homeapp.annotations.data.datasource

import com.example.homeapp.annotations.data.model.toDomain
import com.example.homeapp.annotations.data.service.AnnotationsApi
import com.example.homeapp.annotations.domain.model.AnnotationItem

internal class AnnotationsDataSource(
    private val api: AnnotationsApi
) {

    suspend fun annotations(): List<AnnotationItem> {
        return api.annotations().documents?.map { annotationResponse ->
            annotationResponse.toDomain()
        }.orEmpty()
    }

}
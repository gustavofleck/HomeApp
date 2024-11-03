package com.example.homeapp.annotations.domain.repository

import com.example.homeapp.annotations.domain.model.AnnotationItem

internal interface AnnotationsRepository {

    suspend fun annotations(): List<AnnotationItem>
}
package com.example.homeapp.annotations.domain.usecase

import com.example.homeapp.annotations.domain.model.AnnotationItem
import com.example.homeapp.annotations.domain.repository.AnnotationsRepository

internal class ListAnnotationsUseCase(
    private val repository: AnnotationsRepository
) {

    suspend fun invoke(): List<AnnotationItem> {
        val annotations = repository.annotations()
        val favoriteAnnotations = annotations.filter { it.favorite }
        val nonFavoriteAnnotations = annotations.filter { it.favorite.not() }
        return favoriteAnnotations + nonFavoriteAnnotations
    }

}
package com.example.homeapp.annotations.data.datasource

import com.example.homeapp.annotations.data.model.toDomain
import com.example.homeapp.annotations.data.service.NotesApi
import com.example.homeapp.annotations.domain.model.Note

internal class NotesDataSource(
    private val api: NotesApi
) {

    suspend fun notes(): List<Note> {
        return api.notes().documents?.map { annotationResponse ->
            annotationResponse.toDomain()
        }.orEmpty()
    }

}
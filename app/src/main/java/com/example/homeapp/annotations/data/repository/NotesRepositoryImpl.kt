package com.example.homeapp.annotations.data.repository

import com.example.homeapp.annotations.data.datasource.NotesDataSource
import com.example.homeapp.annotations.domain.repository.NotesRepository

internal class NotesRepositoryImpl(
    private val dataSource: NotesDataSource
): NotesRepository {

    override suspend fun notes() = dataSource.notes()

}
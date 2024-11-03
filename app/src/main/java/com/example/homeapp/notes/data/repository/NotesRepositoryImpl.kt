package com.example.homeapp.notes.data.repository

import com.example.homeapp.notes.data.datasource.NotesDataSource
import com.example.homeapp.notes.domain.repository.NotesRepository

internal class NotesRepositoryImpl(
    private val dataSource: NotesDataSource
): NotesRepository {

    override suspend fun notes() = dataSource.notes()

}
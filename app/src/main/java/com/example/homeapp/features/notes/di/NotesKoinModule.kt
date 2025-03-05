package com.example.homeapp.features.notes.di

import com.example.homeapp.features.notes.data.datasource.NotesDataSource
import com.example.homeapp.features.notes.data.repository.NotesRepositoryImpl
import com.example.homeapp.features.notes.data.service.NotesApi
import com.example.homeapp.features.notes.domain.repository.NotesRepository
import com.example.homeapp.features.notes.domain.usecase.ListNotesUseCase
import com.example.homeapp.network.FirestoreServiceProvider
import com.example.homeapp.features.notes.domain.usecase.AddNoteUseCase
import com.example.homeapp.features.notes.presentation.viewmodel.NotesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

private val dataModule = module {
    single<NotesApi> {
        FirestoreServiceProvider.apiInstance().create<NotesApi>()
    }
    factory {
        NotesDataSource(
            api = get()
        )
    }
    factory<NotesRepository> {
        NotesRepositoryImpl(
            dataSource = get()
        )
    }
}

private val domainModule = module {
    factory {
        ListNotesUseCase(
            repository = get()
        )
    }
    factory {
        AddNoteUseCase(
            repository = get()
        )
    }
}

private val presentationModule = module {
    viewModel {
        NotesViewModel(
            listUseCase = get(),
            addNoteUseCase = get()
        )
    }
}

val notesModule = listOf(dataModule, domainModule, presentationModule)
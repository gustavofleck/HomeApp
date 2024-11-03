package com.example.homeapp.notes.di

import com.example.homeapp.notes.data.datasource.NotesDataSource
import com.example.homeapp.notes.data.repository.NotesRepositoryImpl
import com.example.homeapp.notes.data.service.NotesApi
import com.example.homeapp.notes.domain.repository.NotesRepository
import com.example.homeapp.notes.domain.usecase.ListNotesUseCase
import com.example.homeapp.network.FirestoreServiceProvider
import com.example.homeapp.notes.presentation.viewmodel.NotesViewModel
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
}

private val presentationModule = module {
    viewModel {
        NotesViewModel(
            useCase = get()
        )
    }
}

val annotationModule = listOf(dataModule, domainModule, presentationModule)
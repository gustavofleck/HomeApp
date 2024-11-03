package com.example.homeapp.annotations.di

import com.example.homeapp.annotations.data.datasource.NotesDataSource
import com.example.homeapp.annotations.data.repository.NotesRepositoryImpl
import com.example.homeapp.annotations.data.service.NotesApi
import com.example.homeapp.annotations.domain.repository.NotesRepository
import com.example.homeapp.annotations.domain.usecase.ListNotesUseCase
import com.example.homeapp.network.FirestoreServiceProvider
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

private val presenterModule = module {

}

val annotationModule = listOf(dataModule, domainModule, presenterModule)
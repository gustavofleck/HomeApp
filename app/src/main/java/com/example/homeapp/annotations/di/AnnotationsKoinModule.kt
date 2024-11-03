package com.example.homeapp.annotations.di

import com.example.homeapp.annotations.data.datasource.AnnotationsDataSource
import com.example.homeapp.annotations.data.repository.AnnotationRepositoryImpl
import com.example.homeapp.annotations.data.service.AnnotationsApi
import com.example.homeapp.annotations.domain.repository.AnnotationsRepository
import com.example.homeapp.annotations.domain.usecase.ListAnnotationsUseCase
import com.example.homeapp.network.FirestoreServiceProvider
import org.koin.dsl.module
import retrofit2.create

private val dataModule = module {
    single<AnnotationsApi> {
        FirestoreServiceProvider.apiInstance().create<AnnotationsApi>()
    }
    factory {
        AnnotationsDataSource(
            api = get()
        )
    }
    factory<AnnotationsRepository> {
        AnnotationRepositoryImpl(
            dataSource = get()
        )
    }
}

private val domainModule = module {
    factory {
        ListAnnotationsUseCase(
            repository = get()
        )
    }
}

private val presenterModule = module {

}

val annotationModule = listOf(dataModule, domainModule, presenterModule)
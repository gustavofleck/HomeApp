package com.example.homeapp.features.login.di

import com.example.homeapp.features.login.presentation.viewmodel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        LoginViewModel(
            auth = Firebase.auth
        )
    }
}

val loginModule = listOf(presentationModule)
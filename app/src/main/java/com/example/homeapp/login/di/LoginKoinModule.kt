package com.example.homeapp.login.di

import com.example.homeapp.login.presentation.viewmodel.LoginViewModel
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
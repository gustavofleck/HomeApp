package com.example.homeapp.features.login.presentation.viewmodel

internal sealed class LoginViewState {

    data object Loaded : LoginViewState()
    data object Loading : LoginViewState()
    data object Error : LoginViewState()
}
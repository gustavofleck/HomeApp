package com.example.homeapp.login.presentation.viewmodel

internal sealed class LoginViewState {

    data object Loaded : LoginViewState()
    data object Loading : LoginViewState()
    data object Error : LoginViewState()
}
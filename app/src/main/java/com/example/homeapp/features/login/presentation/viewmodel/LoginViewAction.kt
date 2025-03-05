package com.example.homeapp.features.login.presentation.viewmodel

internal sealed class LoginViewAction {
    data object Login : LoginViewAction()
}
package com.example.homeapp.login.presentation.viewmodel

internal sealed class LoginViewAction {
    data object Login : LoginViewAction()
}
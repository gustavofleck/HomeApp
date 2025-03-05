package com.example.homeapp.features.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewAction.Login
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewState.Error
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewState.Loaded
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewState.Loading
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class LoginViewModel(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow<LoginViewState>(Loaded)
    val state: StateFlow<LoginViewState> = _state

    private val _action: MutableLiveData<LoginViewAction> = MutableLiveData()
    val action: LiveData<LoginViewAction> = _action

    fun onSignInClicked(email: String, password: String) {
        if (isLoginDataValid(email, password)) return

        _state.value = Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _state.value = Loaded
                    _action.postValue(Login)
                } else {
                    _state.value = Error
                }
            }
    }

    private fun isLoginDataValid(email: String, password: String) =
        email.isEmpty() || password.isEmpty()

    fun onRetryClicked() {
        _state.value = Loaded
    }

}
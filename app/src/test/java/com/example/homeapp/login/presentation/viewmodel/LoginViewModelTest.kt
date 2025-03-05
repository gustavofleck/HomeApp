package com.example.homeapp.login.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.cash.turbine.test
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewAction
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewModel
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewState
import com.google.firebase.auth.FirebaseAuth
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val email = "email"
    private val password = "password"
    private val auth = mockk<FirebaseAuth>(relaxed = true)
    private val actionObserver = mockk<Observer<LoginViewAction>>(relaxed = true)
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(auth)
        viewModel.action.observeForever(actionObserver)
    }

    @Test
    fun `onSignInClicked Should not send Login action When firebase login data is empty`() {
        viewModel.onSignInClicked("", "")

        verify(exactly = 0) {
            actionObserver.onChanged(LoginViewAction.Login)
        }
    }

    @Test
    fun `onSignInClicked Should set Loading state When firebase login is called`() = runTest {
        viewModel.state.test {
            assertEquals(LoginViewState.Loaded, awaitItem())
            viewModel.onSignInClicked(email, password)
            assertEquals(LoginViewState.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `onRetryClicked Should reset screen to Loaded state`() = runTest {
        viewModel.state.test {
            viewModel.onRetryClicked()
            assertEquals(LoginViewState.Loaded, awaitItem())
            ensureAllEventsConsumed()
        }
    }

}
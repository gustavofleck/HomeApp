package com.example.homeapp.login.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ds.components.DSHeader
import com.example.ds.components.DSInputText
import com.example.ds.components.DSPasswordInputText
import com.example.ds.components.DSPrimaryButton
import com.example.ds.states.ErrorState
import com.example.ds.states.LoadingState
import com.example.homeapp.R
import com.example.homeapp.login.presentation.viewmodel.LoginViewModel
import com.example.homeapp.login.presentation.viewmodel.LoginViewState

@Composable
internal fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
        DSHeader(title = stringResource(id = R.string.app_name)) {}

        val state by viewModel.state.collectAsState()

        when (state) {
            is LoginViewState.Loading -> LoadingState()
            is LoginViewState.Loaded -> LoginLoadedState(viewModel)
            is LoginViewState.Error -> ErrorState {
                viewModel.onRetryClicked()
            }
        }
    }
}

@Composable
internal fun LoginLoadedState(viewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Row {
        Column(
            Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painterResource(id = R.drawable.home_app_launcher_icon),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = null
            )
            DSInputText(
                placeholder = stringResource(id = R.string.login_email_input_label),
                onValueChange = { email = it },
                label = stringResource(id = R.string.login_email_input_label)
            )
            DSPasswordInputText(
                onValueChange = { password = it }
            )
        }
    }
    DSPrimaryButton(text = stringResource(id = R.string.login_sign_in_button_label)) {
        viewModel.onSignInClicked(email, password)
    }
}
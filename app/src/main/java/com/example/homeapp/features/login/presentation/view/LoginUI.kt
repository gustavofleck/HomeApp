package com.example.homeapp.features.login.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ds.components.header.DSHeader
import com.example.ds.components.inputtext.DSInputText
import com.example.ds.components.inputtext.DSPasswordInputText
import com.example.ds.components.smallbutton.DSPrimarySmallButton
import com.example.ds.components.smallbutton.DSSecondarySmallButton
import com.example.ds.states.ErrorState
import com.example.ds.states.LoadingState
import com.example.homeapp.R
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewModel
import com.example.homeapp.features.login.presentation.viewmodel.LoginViewState

@Composable
internal fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
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

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painterResource(id = R.drawable.home_app_logo),
                modifier = Modifier.size(320.dp).align(Alignment.CenterHorizontally),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            DSInputText(
                modifier = Modifier.width(440.dp),
                placeholder = stringResource(id = R.string.login_email_input_label),
                onValueChange = { email = it },
                label = stringResource(id = R.string.login_email_input_label)
            )
            DSPasswordInputText(
                modifier = Modifier.width(440.dp),
                onValueChange = { password = it }
            )
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column {
            DSPrimarySmallButton(text = stringResource(id = R.string.login_sign_in_button_label)) {
                viewModel.onSignInClicked(email, password)
            }
        }
        Column {
            DSSecondarySmallButton(text = stringResource(id = R.string.login_sign_up_button_label)) { }
        }
    }

}
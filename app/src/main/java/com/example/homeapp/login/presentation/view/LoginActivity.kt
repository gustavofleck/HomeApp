package com.example.homeapp.login.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ds.theme.DesignSystemTheme
import com.example.homeapp.login.presentation.viewmodel.LoginViewAction
import com.example.homeapp.login.presentation.viewmodel.LoginViewModel
import com.example.homeapp.notes.presentation.view.NotesActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesignSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
        actionObserver()
    }

    private fun actionObserver() {
        viewModel.action.observe(this) { action ->
            if (action is LoginViewAction.Login) {
                navigateToNotesActivity()
            }
        }
    }

    private fun navigateToNotesActivity() {
        val intent = NotesActivity.newIntent(this)
        startActivity(intent)
    }
}
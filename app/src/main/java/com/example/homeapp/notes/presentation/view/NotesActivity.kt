package com.example.homeapp.notes.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ds.theme.DesignSystemTheme
import com.example.homeapp.notes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class NotesActivity : AppCompatActivity() {

    private val viewModel by viewModel<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onRetrieveNotes()
        enableEdgeToEdge()
        setContent {
            DesignSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotesScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, NotesActivity::class.java)

    }

}

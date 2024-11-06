package com.example.homeapp.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.ReplayCircleFilled
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ds.components.DSHeader
import com.example.ds.components.DSInputText
import com.example.ds.components.DSPrimaryButton
import com.example.ds.components.DSSecondaryButton
import com.example.homeapp.notes.domain.model.Note
import com.example.homeapp.notes.presentation.viewmodel.NotesViewModel
import com.example.homeapp.notes.presentation.viewmodel.NotesViewState

@Composable
internal fun NotesScreen(
    modifier: Modifier,
    viewModel: NotesViewModel
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Row {
            DSHeader(title = "Anotações") {}
        }
        Row {
            when (state) {
                is NotesViewState.Loading -> NotesLoadingState()

                is NotesViewState.Empty -> NotesEmptyState {
                    viewModel.onAddNoteClicked()
                }

                is NotesViewState.Loaded ->
                    NotesLoadedState((state as? NotesViewState.Loaded)?.notes.orEmpty()) {
                        viewModel.onAddNoteClicked()
                    }

                is NotesViewState.Error -> NotesErrorState {
                    viewModel.onRetrieveNotes()
                }

                is NotesViewState.AddNote -> AddNoteState(
                    onAddNoteConfirm = { text, favorite ->
                        viewModel.onAddNoteConfirm(text, favorite)
                    },
                    onAddNoteCancel = viewModel::onRetrieveNotes
                )
            }
        }
    }
}

@Composable
internal fun NotesLoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            strokeWidth = 8.dp
        )
    }
}

@Composable
internal fun NotesEmptyState(onAddNoteClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally),
                imageVector = Icons.Default.Note,
                contentDescription = "Sem anotações"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                text = "Parece que você ainda não tem anotações, clique no botão '+' para adicionar"
            )
        }
        AddNoteButton(modifier = Modifier.align(Alignment.BottomEnd)) {
            onAddNoteClicked()
        }
    }
}

@Composable
internal fun NotesLoadedState(notes: List<Note>, onAddNoteClicked: () -> Unit) {
    Box {
        Row {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                notes.forEach { note ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .border(
                                width = 4.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.large
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 16.dp, bottom = 16.dp, top = 16.dp)
                                    .align(Alignment.CenterStart),
                                text = note.text,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                            val iconData = setupFavoriteIcon(note.favorite)
                            Icon(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterEnd),
                                imageVector = iconData.first,
                                contentDescription = iconData.second
                            )
                        }
                    }
                }
            }
        }
        AddNoteButton(modifier = Modifier.align(Alignment.BottomEnd)) {
            onAddNoteClicked()
        }
    }
}

@Composable
internal fun AddNoteButton(modifier: Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.secondary, CircleShape),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(88.dp),
            imageVector = Icons.Default.Add,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Adicionar anotação"
        )
    }
}

private fun setupFavoriteIcon(favorite: Boolean) = if (favorite) {
    Pair(Icons.Default.Star, "Favorito")
} else Pair(Icons.Default.StarOutline, "Não favorito")

@Composable
internal fun NotesErrorState(onRetryClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Default.Error,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Error"
        )
        Text(
            text = "Ocorreu um erro inesperado, tente novamente",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(48.dp))
        IconButton(
            onClick = onRetryClicked,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.secondary, CircleShape)
        ) {
            Icon(
                modifier = Modifier.size(120.dp),
                imageVector = Icons.Default.ReplayCircleFilled,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Retry"
            )
        }
    }
}

@Composable
internal fun AddNoteState(
    onAddNoteConfirm: (String, Boolean) -> Unit,
    onAddNoteCancel: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var favorite by remember { mutableStateOf(false) }
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            DSInputText(
                label = "Anotação",
                placeholder = "Escreva aqui sua anotação",
                onValueChange = { text = it },
                maxLines = 15,
                minLines = 15
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = favorite,
                onCheckedChange = { favorite = it }
            )
            Text(text = "Favoritar", style = MaterialTheme.typography.bodyLarge)
        }
        Row {
            DSPrimaryButton(text = "Confirmar") {
                onAddNoteConfirm(text, favorite)
            }
        }
        Row {
            DSSecondaryButton(text = "Cancelar") {
                onAddNoteCancel()
            }
        }
    }
}
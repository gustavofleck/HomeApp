package com.example.homeapp.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.ds.R as DSRes
import com.example.ds.components.header.DSHeader
import com.example.ds.components.inputtext.DSInputText
import com.example.ds.components.button.DSPrimaryButton
import com.example.ds.components.button.DSSecondaryButton
import com.example.ds.states.ErrorState
import com.example.ds.states.LoadingState
import com.example.homeapp.R
import com.example.homeapp.features.notes.domain.model.Note
import com.example.homeapp.features.notes.presentation.viewmodel.NotesViewModel
import com.example.homeapp.features.notes.presentation.viewmodel.NotesViewState

@Composable
internal fun NotesScreen(
    modifier: Modifier,
    viewModel: NotesViewModel
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Row {
            DSHeader(title = stringResource(id = R.string.notes_screen_title)) {}
        }
        Row {
            when (state) {
                is NotesViewState.Loading -> LoadingState()

                is NotesViewState.Empty -> NotesEmptyState {
                    viewModel.onAddNoteClicked()
                }

                is NotesViewState.Loaded ->
                    NotesLoadedState((state as? NotesViewState.Loaded)?.notes.orEmpty()) {
                        viewModel.onAddNoteClicked()
                    }

                is NotesViewState.Error -> ErrorState {
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
internal fun NotesEmptyState(onAddNoteClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                modifier = Modifier
                    .size(dimensionResource(id = DSRes.dimen.icon_small_size))
                    .align(Alignment.CenterHorizontally),
                imageVector = Icons.Default.Note,
                contentDescription = stringResource(id = R.string.no_notes_icon_content_description)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = DSRes.dimen.padding_large))
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.no_notes_screen_message)
            )
        }
        AddNoteButton(modifier = Modifier.align(Alignment.BottomEnd)) {
            onAddNoteClicked()
        }
    }
}

@Composable
internal fun NotesLoadedState(notes: List<Note>, onAddNoteClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = dimensionResource(id = DSRes.dimen.padding_large)),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                notes.forEach { note ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimensionResource(id = DSRes.dimen.padding_large),
                                vertical = dimensionResource(id = DSRes.dimen.padding_medium)
                            )
                            .border(
                                width = dimensionResource(id = DSRes.dimen.border_small),
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.large
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = dimensionResource(id = DSRes.dimen.padding_large),
                                        bottom = dimensionResource(id = DSRes.dimen.padding_large),
                                        top = dimensionResource(id = DSRes.dimen.padding_large)
                                    )
                                    .align(Alignment.CenterStart),
                                text = note.text,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                            val iconData = setupFavoriteIcon(note.favorite)
                            Icon(
                                modifier = Modifier
                                    .padding(dimensionResource(id = DSRes.dimen.padding_large))
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
            .padding(horizontal = dimensionResource(id = DSRes.dimen.padding_large))
            .background(MaterialTheme.colorScheme.primary, CircleShape),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = DSRes.dimen.icon_medium_size)),
            imageVector = Icons.Default.Add,
            tint = MaterialTheme.colorScheme.tertiary,
            contentDescription = stringResource(id = R.string.add_notes_icon_content_description)
        )
    }
}

private fun setupFavoriteIcon(favorite: Boolean) = if (favorite) {
    Pair(Icons.Default.Star, "Favorito")
} else Pair(Icons.Default.StarOutline, "Não favorito")

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
                label = stringResource(id = R.string.add_note_label),
                placeholder = stringResource(id = R.string.add_note_placeholder),
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
            Text(
                text = stringResource(id = R.string.add_note_favorite_checkbox_label),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row {
            DSPrimaryButton(text = stringResource(id = R.string.add_note_confirm_button_label)) {
                onAddNoteConfirm(text, favorite)
            }
        }
        Row {
            DSSecondaryButton(text = stringResource(id = R.string.add_note_cancel_button_label)) {
                onAddNoteCancel()
            }
        }
    }
}
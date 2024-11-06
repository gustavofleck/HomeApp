package com.example.ds.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ReplayCircleFilled
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.ds.R

@Composable
fun ErrorState(onRetryClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_small_size)),
            imageVector = Icons.Default.Error,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Error"
        )
        Text(
            text = "Ocorreu um erro inesperado, tente novamente",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))
        IconButton(
            onClick = onRetryClicked,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                .background(MaterialTheme.colorScheme.secondary, CircleShape)
        ) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_large_size)),
                imageVector = Icons.Default.ReplayCircleFilled,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Tentar novamente"
            )
        }
    }
}
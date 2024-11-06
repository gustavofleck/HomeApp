package com.example.ds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ds.R

@Composable
fun DSHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    navigationBack: (() -> Unit)? = null,
    navigationListener: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(
                    bottomStart = dimensionResource(id = R.dimen.border_large),
                    bottomEnd = dimensionResource(id = R.dimen.border_large)
                )
            )
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_large),
                horizontal = dimensionResource(id = R.dimen.border_medium)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationBack?.let {
                    IconButton(onClick = it) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.icon_small_size))
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )

                    subtitle?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        IconButton(onClick = navigationListener) {
            Icon(
                imageVector = Icons.Default.Help,
                contentDescription = "Ajuda",
                tint = Color.White,
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_small_size))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DSHeaderPreview() {
    DSHeader(title = "Title", subtitle = "Subtitle", navigationBack = {}) {}
}
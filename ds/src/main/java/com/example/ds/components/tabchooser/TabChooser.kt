package com.example.ds.components.tabchooser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ds.R

@Composable
fun DSTabChooser(
    text: String,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .height(dimensionResource(id = R.dimen.bar_medium_height))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousClick) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_small_size)),
                painter = painterResource(id = R.drawable.ic_ds_arrow_left),
                contentDescription = "Retornar seleção",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        IconButton(onClick = onNextClick) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_small_size)),
                painter = painterResource(id = R.drawable.ic_ds_arrow_right),
                contentDescription = "Avançar seleção",
                tint = MaterialTheme.colorScheme.secondary
            )
        }

    }
}

@Preview
@Composable
fun DSTabChooserPreview() {
    DSTabChooser(
        text = "Seleção 1",
        onPreviousClick = {},
        onNextClick = {}
    )
}
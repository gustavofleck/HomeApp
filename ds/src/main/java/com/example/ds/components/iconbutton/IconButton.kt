package com.example.ds.components.iconbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ds.R

@Composable
fun DSIconButton(
    modifier: Modifier,
    painter: Painter,
    contentDescription: String,
    text: String? = null,
    onClick: () -> Unit
) {
    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(8.dp)
                )
                .size(72.dp)
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painter,
                contentDescription = contentDescription
            )

        }
        text?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview
@Composable
fun DSIconButtonPreview() {
    DSIconButton(
        modifier = Modifier,
        painter = painterResource(id = R.drawable.ic_ds_android_placeholder),
        contentDescription = "",
        text = "Teste"
    ) {

    }
}
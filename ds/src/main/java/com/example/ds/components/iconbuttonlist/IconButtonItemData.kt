package com.example.ds.components.iconbuttonlist

import androidx.compose.ui.graphics.painter.Painter

data class IconButtonItemData(
    val icon: Painter,
    val text: String,
    val onClick: () -> Unit
)

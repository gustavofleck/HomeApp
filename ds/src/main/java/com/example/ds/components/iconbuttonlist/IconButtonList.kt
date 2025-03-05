package com.example.ds.components.iconbuttonlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ds.R
import com.example.ds.components.iconbutton.DSIconButton

@Composable
fun DSIconButtonGrid(
    modifier: Modifier,
    itemList: List<IconButtonItemData>
) {
    LazyVerticalGrid(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_large)),
        columns = GridCells.Adaptive(minSize = 100.dp)
    ) {
        items(itemList) { item ->
            DSIconButton(
                modifier = Modifier,
                painter = item.icon,
                contentDescription = item.text,
                text = item.text,
            ) {}
        }
    }
}

@Preview
@Composable
fun DSIconButtonGridPreview() {
    DSIconButtonGrid(modifier = Modifier, itemList = listOf(
        IconButtonItemData(
            painterResource(id = R.drawable.ic_ds_android_placeholder),
            "Android"
        ) {},
        IconButtonItemData(
            painterResource(id = R.drawable.ic_ds_android_placeholder),
            "Android"
        ) {},
        IconButtonItemData(
            painterResource(id = R.drawable.ic_ds_android_placeholder),
            "Android"
        ) {},
        IconButtonItemData(
            painterResource(id = R.drawable.ic_ds_android_placeholder),
            "Android"
        ) {},
        IconButtonItemData(
            painterResource(id = R.drawable.ic_ds_android_placeholder),
            "Android"
        ) {}

    ))
}
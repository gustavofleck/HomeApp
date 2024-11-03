package com.example.ds.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ds.theme.primary
import com.example.ds.theme.secondary
import com.example.ds.theme.tertiary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSInputText(
    modifier: Modifier = Modifier.fillMaxWidth().padding(16.dp),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String? = null,
    placeholder: String? = null,
    isError: Boolean = false
) {

    var text by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = { label?.let { Text(text = label)}},
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) secondary else primary,
            unfocusedBorderColor = if (isError) tertiary else secondary
        ),
        placeholder = { placeholder?.let { Text(text = placeholder) } },
        modifier = modifier
    )
}

@Preview
@Composable
fun DSInputTextPreview() {
    DSInputText(value = "Hello", onValueChange = {}, isError = true)
}
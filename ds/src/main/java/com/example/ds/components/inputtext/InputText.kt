package com.example.ds.components.inputtext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ds.theme.primary
import com.example.ds.theme.secondary
import com.example.ds.theme.tertiary

@Composable
fun DSInputText(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String? = null,
    placeholder: String? = null,
    isError: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = if (isError) secondary else primary,
            unfocusedBorderColor = if (isError) tertiary else secondary,
            unfocusedPlaceholderColor = secondary,
            focusedLabelColor = secondary
        ),
        placeholder = { placeholder?.let { Text(text = placeholder) } },
        modifier = modifier.padding(16.dp),
        maxLines = maxLines,
        minLines = minLines
    )
}

@Composable
fun DSPasswordInputText(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false
) {

    var text by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = { Text(text = "Senha")},
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isError) secondary else primary,
            unfocusedBorderColor = if (isError) tertiary else secondary,
            unfocusedPlaceholderColor = secondary
        ),
        placeholder = { Text(text = "Senha") },
        modifier = modifier.padding(16.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview
@Composable
fun DSInputTextPreview() {
    DSInputText(value = "Hello", onValueChange = {}, isError = false)
}
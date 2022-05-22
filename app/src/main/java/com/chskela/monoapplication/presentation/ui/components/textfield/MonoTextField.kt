package com.chskela.monoapplication.presentation.ui.components.textfield

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MonoTextField(
    label: String = "",
    value: String,
    textStyle: TextStyle = MaterialTheme.typography.subtitle2,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 5,
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Text(text = label,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.secondary)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        textStyle = textStyle.copy(color = MaterialTheme.colors.onSurface),
        shape = MaterialTheme.shapes.medium,
        placeholder = placeholder,
        singleLine = singleLine,
        maxLines = maxLines,
        value = value,
        onValueChange = { onValueChange(it) },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
    )
}
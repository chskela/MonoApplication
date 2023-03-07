package com.chskela.monoapplication.presentation.ui.components.textfield

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonoTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 5,
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                ),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
            shape = MaterialTheme.shapes.medium,
            placeholder = placeholder,
            singleLine = singleLine,
            maxLines = maxLines,
            value = value,
            onValueChange = { onValueChange(it) },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItem() {
    MonoApplicationTheme {
        MonoTextField(
            value = "value"
        )
    }
}

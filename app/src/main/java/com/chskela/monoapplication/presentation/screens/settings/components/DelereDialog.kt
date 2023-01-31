package com.chskela.monoapplication.presentation.screens.settings.components

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun DeleteDialog(
    openDialog: Boolean,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = stringResource(R.string.attention),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.all_data_will_be_deleted),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        stringResource(R.string.confirm),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        stringResource(R.string.dismiss),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Light SettingsScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDeleteDialog() {
    MonoApplicationTheme {
        DeleteDialog(true)
    }
}
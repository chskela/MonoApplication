package com.chskela.monoapplication.presentation.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.settings.components.DeleteDialog
import com.chskela.monoapplication.presentation.screens.settings.components.SettingItem
import com.chskela.monoapplication.presentation.screens.settings.models.SettingUiItem
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onCategory: () -> Unit = {},
    onCurrency: () -> Unit = {},
    deleteAllData: () -> Unit = {},
    switchTheme: (Boolean) -> Unit = {},
) {
    var openDialog by remember { mutableStateOf(false) }
    DeleteDialog(openDialog,
        onDismiss = { openDialog = false },
        onConfirm = {
            openDialog = false
            deleteAllData()
        }
    )
    Scaffold(
        topBar = {
            MonoTopAppBar(title = stringResource(id = R.string.settings), navigationIcon = {
                Spacer(modifier = Modifier.size(48.dp))
            })
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp)
        ) {
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.category),
                    leftIcon = R.drawable.settings_squares_four,
                    rightIcon = R.drawable.settings_caret_circle_right,
                    color = MaterialTheme.colorScheme.onSurface,
                    onClick = { onCategory() }
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.currency),
                    leftIcon = R.drawable.settings_currency_circle_dollar,
                    rightIcon = R.drawable.settings_caret_circle_right,
                    color = MaterialTheme.colorScheme.onSurface,
                    onClick = { onCurrency() }
                ),
            )
            Spacer(modifier = Modifier.height(24.dp))
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.dark_mode),
                    leftIcon = R.drawable.settings_mode,
                    color = MaterialTheme.colorScheme.onSurface,
                    onClick = { switchTheme(!isDarkTheme) }
                ),
                rightIcon = {
                    Switch(
                        modifier = Modifier.semantics { contentDescription = "Demo" },
                        checked = isDarkTheme,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.primaryContainer,
                            checkedTrackColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        onCheckedChange = { switchTheme(!isDarkTheme) }
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.delete_all_data),
                    leftIcon = R.drawable.settings_trash,
                    color = MaterialTheme.colorScheme.error,
                    onClick = { openDialog = true }
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Light SettingsScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSettingsScreen() {
    MonoApplicationTheme {
        SettingsScreen(true)
    }
}
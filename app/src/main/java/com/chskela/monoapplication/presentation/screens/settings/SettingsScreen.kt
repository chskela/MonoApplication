package com.chskela.monoapplication.presentation.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.settings.components.SettingItem
import com.chskela.monoapplication.presentation.screens.settings.models.SettingUiItem
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun SettingsActivityScreen(
    onCategory: () -> Unit,
    onCurrency: () -> Unit,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    SettingsScreen(
        onCategory = onCategory,
        onCurrency = onCurrency,
        deleteAllData = settingsViewModel::deleteAllData
    )
}

@Composable
fun SettingsScreen(
    onCategory: () -> Unit = {},
    onCurrency: () -> Unit = {},
    deleteAllData: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            MonoTopAppBar(title = stringResource(id = R.string.settings), navigationIcon = {
                Spacer(modifier = Modifier.size(48.dp))
            })
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        ) {
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.category),
                    leftIcon = R.drawable.settings_squares_four,
                    rightIcon = R.drawable.settings_caret_circle_right,
                    color = MaterialTheme.colors.onSurface,
                    onClick = { onCategory() }
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.currency),
                    leftIcon = R.drawable.settings_currency_circle_dollar,
                    rightIcon = R.drawable.settings_caret_circle_right,
                    color = MaterialTheme.colors.onSurface,
                    onClick = { onCurrency() }
                ),
            )
            Spacer(modifier = Modifier.height(24.dp))
            SettingItem(
                settingUiItem = SettingUiItem(
                    title = stringResource(id = R.string.delete_all_data),
                    leftIcon = R.drawable.settings_trash,
                    color = MaterialTheme.colors.error,
                    onClick = deleteAllData
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
        SettingsScreen()
    }
}
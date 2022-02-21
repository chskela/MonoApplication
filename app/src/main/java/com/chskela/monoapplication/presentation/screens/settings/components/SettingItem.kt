package com.chskela.monoapplication.presentation.screens.settings.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.settings.models.SettingUiItem
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    settingUiItem: SettingUiItem,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = MaterialTheme.shapes.medium)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium)
            .clickable { settingUiItem.onClick }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(id = settingUiItem.leftIcon),
                contentDescription = "Category",
                tint = settingUiItem.color
            )
            Text(text = settingUiItem.title,
                style = MaterialTheme.typography.body1,
                color = settingUiItem.color,
                modifier = modifier.padding(start = 12.dp))
        }

        if (settingUiItem.rightIcon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = settingUiItem.rightIcon),
                contentDescription = "Category",
                tint = settingUiItem.color
            )
        }

    }
}

@Composable
fun TemplateSettingItem( modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = MaterialTheme.shapes.medium)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}

@Preview(showBackground = true, name = "Light SettingItem")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSettingItem() {
    MonoApplicationTheme {
        SettingItem(
            settingUiItem = SettingUiItem(
                title = "Category",
                leftIcon = R.drawable.settings_squares_four,
                rightIcon = R.drawable.settings_caret_circle_right,
                color = MaterialTheme.colors.onSurface
            )
        )
    }
}

@Preview(showBackground = true, name = "Light SettingItemMode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSettingItemMode() {
    MonoApplicationTheme {
        SettingItem(
            settingUiItem = SettingUiItem(
                title = "Dark mode",
                leftIcon = R.drawable.settings_mode,
                rightIcon = R.drawable.toggle_right,
                color = MaterialTheme.colors.onSurface
            )
        )
    }
}
@Preview(showBackground = true, name = "Light SettingItem - Delete")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSettingItemDelete() {
    MonoApplicationTheme {
        SettingItem(
            settingUiItem = SettingUiItem(
                title = "Delete All Data",
                leftIcon = R.drawable.settings_trash,
                color = MaterialTheme.colors.error
            )
        )
    }
}


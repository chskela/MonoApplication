package com.chskela.monoapplication.presentation.ui.components.bottomnavigation

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.chskela.monoapplication.presentation.MonoScreens
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonoBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    items: List<MonoScreens> = emptyList(),
    selectedItem: MonoScreens,
    onClick: (MonoScreens) -> Unit = {}
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
    ) {
        items.forEachIndexed { index, item ->

            if (item.menuItem != null) {
                val label = stringResource(item.menuItem.label)
                val isSelected = selectedItem == item

                BottomNavigationItem(
                    unselectedContentColor = contentColor,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                if (isSelected) item.menuItem.iconActive else item.menuItem.icon),
                            contentDescription = label,
                        )
                    },
                    label = { Text(text = label, style = MaterialTheme.typography.overline) },
                    selected = isSelected,
                    onClick = { onClick(item) },
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Light MonoBottomNavigation")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoBottomNavigation() {
    MonoApplicationTheme {
        Surface {
            MonoBottomNavigation(selectedItem = MonoScreens.Setting)
        }
    }
}
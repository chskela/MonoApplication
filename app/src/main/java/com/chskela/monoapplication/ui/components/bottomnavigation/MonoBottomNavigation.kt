package com.chskela.monoapplication.ui.components.bottomnavigation

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
import com.chskela.monoapplication.ui.theme.MonoApplicationTheme

@Composable
fun MonoBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    items: List<MenuItem> = BottomNavigationData.getData(),
    selectedItem: Int,
    onClick: (Int) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
    ) {
        items.forEachIndexed { index, item ->

            val label = item.label
            val isSelected = selectedItem == index

            BottomNavigationItem(
                unselectedContentColor = contentColor,
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.iconActive else item.icon,
                        contentDescription = label,
                    )
                },
                label = { Text(text = label, style = MaterialTheme.typography.overline) },
                selected = isSelected,
                onClick = { onClick(index) },
            )
        }
    }
}


@Preview(showBackground = true, name = "Light MonoBottomNavigation")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoBottomNavigation() {
    MonoApplicationTheme() {
        Surface() {
            var selectedItem by remember { mutableStateOf(0) }
            MonoBottomNavigation(selectedItem = selectedItem,  onClick = { selectedItem = it })
        }
    }
}
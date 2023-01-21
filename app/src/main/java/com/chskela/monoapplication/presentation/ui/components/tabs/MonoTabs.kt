package com.chskela.monoapplication.presentation.ui.components.tabs

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonoTabs(
    modifier: Modifier = Modifier,
    state: Int,
    titles: List<String>,
    onSelect: (Int) -> Unit = {},
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = state,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[state]),
                color = MaterialTheme.colorScheme.primary
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        titles.forEachIndexed { index, title ->
            val selected = state == index
            val color: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary
                },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            Tab(
                text = {
                    Text(
                        text = title, style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                },
                selected = selected,
                onClick = { onSelect(index) }
            )
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItem() {
    MonoApplicationTheme {
        MonoTabs(
            state = 0,
            titles = listOf("test1", "test2")
        )
    }
}

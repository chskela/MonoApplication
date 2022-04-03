package com.chskela.monoapplication.presentation.ui.components.tabs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MonoTabs(
    modifier: Modifier = Modifier,
    state: Int,
    titles: List<String>,
    onSelect: (Int) -> Unit,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = state,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[state]),
                color = MaterialTheme.colors.primary
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        titles.forEachIndexed { index, title ->
            val selected = state == index
            val color: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.secondaryVariant
                },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            Tab(
                text = {
                    Text(
                        text = title, style = MaterialTheme.typography.body1,
                        color = color
                    )
                },
                selected = selected,
                onClick = { onSelect(index) }
            )
        }
    }
}
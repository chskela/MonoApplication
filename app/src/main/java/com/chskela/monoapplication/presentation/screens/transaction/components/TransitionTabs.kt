package com.chskela.monoapplication.presentation.screens.transaction.components

import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TransitionTabs(state: Int, titles: List<String>, onSelect: (Int) -> Unit) {
    TabRow(
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
            Tab(
                text = {
                    Text(text = title, style = MaterialTheme.typography.body1,
                        color = if (selected) {
                            MaterialTheme.colors.primary
                        } else {
                            MaterialTheme.colors.secondaryVariant
                        })
                },
                selected = selected,
                onClick = { onSelect(index) }
            )
        }
    }
}
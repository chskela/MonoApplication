package com.chskela.monoapplication.presentation.ui.components.monocategorysurface

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUi
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import kotlin.math.ceil

@Composable
fun CategoryGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val ratio = constraints.maxWidth / placeables[0].width
        val padding = (constraints.maxWidth - placeables[0].width * ratio) / ratio
        layout(
            width = placeables[0].width * ratio + padding * (ratio - 1),
            height = (placeables[0].height + padding) * ceil(placeables.size.toDouble() / ratio).toInt()
        ) {
            var xPosition = 0
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.place(
                    x = xPosition,
                    y = yPosition
                )
                val isNextRow = xPosition + placeable.width * 2 > constraints.maxWidth
                xPosition = if (!isNextRow) xPosition + placeable.width + padding else 0
                yPosition = if (isNextRow) yPosition + placeable.height + padding else yPosition
            }
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen", showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewCategoryGrid() {
    MonoApplicationTheme {
        CategoryGrid {
            listOf(
                CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = R.drawable.category_bank,
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(id = 0, title = "Add more")).map { CategoryItem(categoryUi = it) }
        }
    }
}
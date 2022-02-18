package com.chskela.monoapplication.presentation.screens.category.models

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.category.CategoryItem
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

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

        val ratio = constraints.maxWidth / (placeables[0].width + 4)
        layout(constraints.maxWidth, (placeables[0].width + 4) * (placeables.size / ratio) + 160) {
            var xPosition = 0
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.place(
                    x = xPosition,
                    y = yPosition
                )
                val isNextRow = xPosition + placeable.width * 2 > constraints.maxWidth
                xPosition = if (!isNextRow) xPosition + placeable.width + 4 else 0
                yPosition = if (isNextRow) yPosition + placeable.height + 4 else yPosition
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
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(title = "Add more")).map { CategoryItem(categoryUi = it) }
        }
    }
}
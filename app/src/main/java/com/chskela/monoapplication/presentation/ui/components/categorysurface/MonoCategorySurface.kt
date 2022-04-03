package com.chskela.monoapplication.presentation.ui.components.categorysurface

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MonoCategorySurface(
    modifier: Modifier = Modifier,
    listCategoryUi: List<CategoryUi>,
    title: String,
    selectedCategory: Long = 0,
    onClickItem: (Long) -> Unit = {},
    onLongClick: (Long) -> Unit = {}
) {
    Row(modifier = modifier) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (listCategoryUi.isNotEmpty()) {
                AnimatedContent(targetState = listCategoryUi) { targetState ->
                    CategoryGrid {
                        targetState.map {
                            val selected = selectedCategory == it.id
                            CategoryItem(
                                categoryUi = it,
                                onClick = onClickItem,
                                onLongClick = onLongClick,
                                selected = selected
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light MonoCategorySurface", showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewMonoCategorySurface() {
    MonoApplicationTheme {
        MonoCategorySurface(
            listCategoryUi = listOf(
                CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(
                    id = 0,
                    icon = "bank",
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(id = 0, title = "Add more")
            ), title = "Category"
        )
    }
}
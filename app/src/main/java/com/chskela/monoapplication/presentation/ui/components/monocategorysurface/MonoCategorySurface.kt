package com.chskela.monoapplication.presentation.ui.components.monocategorysurface

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUi

@Composable
fun MonoCategorySurface(modifier:Modifier = Modifier, listCategoryUi: List<CategoryUi>, title: String) {
    Row(modifier = modifier) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (listCategoryUi.isNotEmpty()) {
                    CategoryGrid {
                        listCategoryUi.map {
                            CategoryItem(categoryUi = it)
                        }
                    }
                }
            }
        }
    }
}
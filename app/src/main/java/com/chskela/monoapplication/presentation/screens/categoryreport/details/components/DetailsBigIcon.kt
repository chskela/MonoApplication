package com.chskela.monoapplication.presentation.screens.categoryreport.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.theme.Expense

@Composable
fun DetailsBigIcon(
    categoryUi: CategoryUi
) {
    Box(
        modifier = Modifier
            .size(312.dp)
            .background(
                color = Expense.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            categoryUi.icon?.let {
                Icon(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(
                        id = iconsMap.getOrDefault(
                            it,
                            R.drawable.category_bank
                        )
                    ),
                    contentDescription = "",
                    tint = Expense
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            categoryUi.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}
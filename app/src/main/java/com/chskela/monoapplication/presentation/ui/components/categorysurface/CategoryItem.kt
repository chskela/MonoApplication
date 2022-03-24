package com.chskela.monoapplication.presentation.ui.components.categorysurface

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryUi: CategoryUi,
    selected: Boolean = false,
    onClick: (Long) -> Unit = {},
) {
    val borderColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant
    val contentColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface

    Box(
        modifier = modifier
            .size(width = 92.dp, height = 82.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = MaterialTheme.shapes.large
            )
            .background(color = MaterialTheme.colors.surface, shape = MaterialTheme.shapes.large)
            .clickable { onClick(categoryUi.id) },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            categoryUi.icon?.let {
                Icon(imageVector = ImageVector.vectorResource(it),
                    contentDescription = categoryUi.title,
                    tint = contentColor,
                    modifier = Modifier.padding(bottom = 8.dp))
            }
            Text(text = categoryUi.title,
                style = MaterialTheme.typography.caption,
                color = contentColor)
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItem() {
    MonoApplicationTheme {
        CategoryItem(categoryUi = CategoryUi(
            id = 0,
            icon = R.drawable.category_bank,
            title = stringResource(id = R.string.category_bank)
        )
        )
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen no icon")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItemNoIcon() {
    MonoApplicationTheme {
        CategoryItem(categoryUi = CategoryUi(id = 0, title = "Add more"))
    }
}
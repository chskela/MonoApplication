package com.chskela.monoapplication.presentation.ui.components.categorysurface

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryUi: CategoryUi,
    selected: Boolean = false,
    onClick: (Long) -> Unit = {},
    onLongClick: (Long) -> Unit = {},
) {
    val (id, icon, title) = categoryUi

    val borderColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    val contentColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .size(width = 92.dp, height = 82.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = MaterialTheme.shapes.large
            )
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.large
            )
            .combinedClickable(
                enabled = true,
                onClick = { onClick(id) },
                onLongClick = { onLongClick(id) },
                onDoubleClick = {}
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            if (icon.isNotBlank()) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        iconsMap.getOrDefault(icon, R.drawable.category_bank)
                    ),
                    contentDescription = title,
                    tint = contentColor
                )
            }

            if (icon.isNotBlank() && title.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (title.isNotBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItem() {
    MonoApplicationTheme {
        CategoryItem(
            categoryUi = CategoryUi(
                id = 0,
                icon = "bank",
                title = stringResource(id = R.string.category_bank)
            )
        )
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true, name = "Light CategoryScreen no icon")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItemNoIcon() {
    MonoApplicationTheme {
        CategoryItem(categoryUi = CategoryUi(id = 0, title = "Add more"))
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true, name = "Light CategoryScreen no icon")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItemNoTitle() {
    MonoApplicationTheme {
        CategoryItem(
            categoryUi = CategoryUi(
                id = 0,
                title = stringResource(id = R.string.category_bank)
            )
        )
    }
}
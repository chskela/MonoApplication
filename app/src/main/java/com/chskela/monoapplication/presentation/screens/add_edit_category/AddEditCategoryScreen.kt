package com.chskela.monoapplication.presentation.screens.add_edit_category

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.add_edit_category.models.AddEditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun EditCategoryScreen(
    uiState: AddEditCategoryUiState,
    onEvent: (AddEditCategoryEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val titles = listOf(stringResource(id = R.string.expense), stringResource(id = R.string.income))
    Scaffold(
        topBar = {
            val titleId =
                if (uiState.isNewCategory) R.string.add_category else R.string.edit_category
            val actionTitleId = if (uiState.isNewCategory) R.string.add else R.string.save

            MonoTopAppBar(
                title = stringResource(id = titleId),
                actions = {
                    IconButton(onClick = {
                        onEvent(
                            if (uiState.isNewCategory) {
                                AddEditCategoryEvent.AddCategory
                            } else {
                                AddEditCategoryEvent.UpdateCategory
                            }
                        )
                        onBack()
                    }) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            text = stringResource(id = actionTitleId)
                        )
                    }
                },
                onNavigation = onBack
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            MonoTabs(
                state = uiState.currentTab,
                titles = titles,
                onSelect = { onEvent(AddEditCategoryEvent.SelectTab(it)) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            MonoTextField(
                label = stringResource(id = R.string.category_name),
                value = uiState.categoryName,
                onValueChange = { onEvent(AddEditCategoryEvent.ChangeCategoryName(it)) }
            )
            Spacer(modifier = Modifier.height(40.dp))
            MonoCategorySurface(
                listCategoryUi = uiState.icons,
                title = stringResource(id = R.string.icon),
                selectedCategory = uiState.icons.firstOrNull { it.icon == uiState.icon }?.id ?: -1,
                onClickItem = { onEvent(AddEditCategoryEvent.ChangeCategoryIcon(it)) }
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, name = "Light EditCategoryScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewEditCategoryScreen() {
    MonoApplicationTheme {
        EditCategoryScreen(
            uiState = AddEditCategoryUiState(
                icons = listOf(
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
                    CategoryUi(id = 0, title = "Add more")
                ),
            )
        )
    }
}
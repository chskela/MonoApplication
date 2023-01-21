package com.chskela.monoapplication.presentation.screens.add_edit_category

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.add_edit_category.models.AddAndEditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAndEditCategoryScreen(
    uiState: AddAndEditCategoryUiState,
    onEvent: (AddAndEditCategoryEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val titles = listOf(stringResource(id = R.string.expense), stringResource(id = R.string.income))
    Scaffold(
        topBar = {
            val titleId =
                if (uiState.isNewCategory) R.string.add_category else R.string.edit_category
            val contentDescription = if (uiState.isNewCategory) R.string.add else R.string.save
            val imageVector = if (uiState.isNewCategory) Icons.Default.Add else Icons.Default.Edit

            MonoTopAppBar(
                title = stringResource(id = titleId),
                actions = {
                    IconButton(onClick = {
                        onEvent(
                            if (uiState.isNewCategory) {
                                AddAndEditCategoryEvent.AddAndCategory
                            } else {
                                AddAndEditCategoryEvent.UpdateCategoryAnd
                            }
                        )
                        onBack()
                    }) {
                        Icon(
                            imageVector = imageVector,
                            contentDescription = stringResource(id = contentDescription)
                        )
                    }
                },
                onNavigation = onBack
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
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
                onSelect = { onEvent(AddAndEditCategoryEvent.SelectTab(it)) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            MonoTextField(
                label = stringResource(id = R.string.category_name),
                value = uiState.categoryName,
                onValueChange = { onEvent(AddAndEditCategoryEvent.ChangeCategoryNameAnd(it)) }
            )
            Spacer(modifier = Modifier.height(40.dp))
            MonoCategorySurface(
                listCategoryUi = uiState.icons,
                title = stringResource(id = R.string.icon),
                selectedCategory = uiState.icons.firstOrNull { it.icon == uiState.icon }?.id ?: -1,
                onClickItem = { onEvent(AddAndEditCategoryEvent.ChangeCategoryIconAnd(it)) }
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
        AddAndEditCategoryScreen(
            uiState = AddAndEditCategoryUiState(
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
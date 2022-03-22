package com.chskela.monoapplication.presentation.screens.edit_category

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.edit_category.models.EditCategoryUiState

import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun EditCategoryActivityScreen(
    categoryViewModel: EditCategoryViewModel = hiltViewModel(),
) {
    EditCategoryScreen(
        uiState = categoryViewModel.uiState.value,
        onEvent = categoryViewModel::onEvent
    )
}

@Composable
fun EditCategoryScreen(
    uiState: EditCategoryUiState,
    onEvent: (EditCategoryEvent) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.category),
                actions = {
                    Text(
                        modifier = Modifier.clickable { onEvent(EditCategoryEvent.AddCategory) },
                        text = stringResource(id = R.string.add)
                    )
                })
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            MonoTextField(label = stringResource(id = R.string.category_name), value = "")
            Spacer(modifier = Modifier.height(40.dp))
            MonoCategorySurface(
                listCategoryUi = uiState.icons,
                title = stringResource(id = R.string.icon)
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
            uiState = EditCategoryUiState(
                icons = listOf(
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
                    CategoryUi(id = 0, title = "Add more")
                ),
            )
        )
    }
}
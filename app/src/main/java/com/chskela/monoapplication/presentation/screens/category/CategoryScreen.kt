package com.chskela.monoapplication.presentation.screens.category

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryActivityScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onClick: (Long) -> Unit
) {
    CategoryScreen(
        uiState = categoryViewModel.uiState.value,
        onBack = onBack,
        onClick = onClick
    )
}

@Composable
fun CategoryScreen(uiState: CategoryUiState, onBack: () -> Unit = {}, onClick: (Long) -> Unit = {}) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            MonoTopAppBar(title = stringResource(id = R.string.category), onNavigation = onBack)
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MonoCategorySurface(
                listCategoryUi = uiState.expenseList.plus(CategoryUi(id = -1, title = "Add more")),
                title = stringResource(id = R.string.expense),
                onClickItem = onClick
            )
            Spacer(modifier = Modifier.height(40.dp))
            MonoCategorySurface(
                listCategoryUi = uiState.incomeList.plus(CategoryUi(id = -1, title = "Add more")),
                title = stringResource(id = R.string.income),
                onClickItem = onClick
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryScreen() {
    MonoApplicationTheme {
        CategoryScreen(
            uiState = CategoryUiState(
                expenseList = listOf(
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
                incomeList = listOf(
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
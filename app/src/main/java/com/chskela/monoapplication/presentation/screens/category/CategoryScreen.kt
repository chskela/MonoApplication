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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUi
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import com.chskela.monoapplication.presentation.ui.components.monocategorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryActivityScreen(
    categoryViewModel: CategoryViewModel = viewModel(),
) {
    CategoryScreen(
        uiState = categoryViewModel.uiState.value,
//        onSelectedCurrency = currencyViewModel::selectDefaultCurrency
    )
}

@Composable
fun CategoryScreen(uiState: CategoryUiState, lastElement: CategoryUi? = null) {
    val scrollState = rememberScrollState()

    fun listWithLastElement(listCategoryUi: List<CategoryUi>) =
        if (lastElement != null) listCategoryUi + lastElement else listCategoryUi

    Scaffold(topBar = {
        MonoTopAppBar(title = stringResource(id = R.string.category))
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
                listCategoryUi = listWithLastElement(uiState.expenseList),
                title = stringResource(id = R.string.expense)
            )
            Spacer(modifier = Modifier.height(40.dp))
            MonoCategorySurface(
                listCategoryUi = listWithLastElement(uiState.incomeList),
                title = stringResource(id = R.string.income)
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
        CategoryScreen(uiState = CategoryUiState(
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
                CategoryUi(id = 0, title = "Add more")),
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
                CategoryUi(id = 0, title = "Add more")),
        )
        )
    }
}
package com.chskela.monoapplication.presentation.screens.category

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.category.components.CategoryItem
import com.chskela.monoapplication.presentation.screens.category.components.CategoryGrid
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUi
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryActivityScreen(
    categoryViewModel: CategoryViewModel = viewModel()
) {
    CategoryScreen(
        uiState = categoryViewModel.uiState.value,
//        onSelectedCurrency = currencyViewModel::selectDefaultCurrency
    )
}

@Composable
fun CategoryScreen(uiState: CategoryUiState) {
    val scrollState = rememberScrollState()
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
            ExpenseIncomeSurface(listCategoryUi = uiState.expenseList,
                title = stringResource(id = R.string.expense))
            ExpenseIncomeSurface(listCategoryUi = uiState.incomeList,
                title = stringResource(id = R.string.income))
        }
    }
}

@Composable
fun ExpenseIncomeSurface(listCategoryUi: List<CategoryUi>, title: String) {
    Row(modifier = Modifier.padding(bottom = 40.dp)) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CategoryGrid {
                    listCategoryUi.map {
                        CategoryItem(categoryUi = it)
                    }
                }
            }
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
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(title = "Add more")),
            incomeList = listOf(
                CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ), CategoryUi(
                    icon = ImageVector.vectorResource(id = R.drawable.category_bank),
                    title = stringResource(id = R.string.category_bank)
                ),
                CategoryUi(title = "Add more")),
        )
        )
    }
}
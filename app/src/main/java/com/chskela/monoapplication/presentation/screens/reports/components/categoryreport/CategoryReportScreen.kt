package com.chskela.monoapplication.presentation.screens.reports.components.categoryreport

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryReportScreen(
    expenseList: List<CategoryUi> = emptyList(),
    incomeList: List<CategoryUi> = emptyList(),
    onSelectCategory: (Long) -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        MonoCategorySurface(
            listCategoryUi = expenseList,
            title = stringResource(id = R.string.expense),
            onClickItem = onSelectCategory,
        )
        MonoCategorySurface(
            listCategoryUi = incomeList,
            title = stringResource(id = R.string.income),
            onClickItem = onSelectCategory,
        )
    }
}

@Preview(showBackground = true, name = "Light CategoryReportScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryReportScreen() {
    MonoApplicationTheme {
        CategoryReportScreen()
    }
}
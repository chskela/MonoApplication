package com.chskela.monoapplication.presentation.screens.categoryreport

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.categoryreport.models.CategoryReportUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryReportActivityScreen(
    categoryReportViewModels: CategoryReportViewModels = hiltViewModel(),
    onSelectCategory: (Long) -> Unit
) {
    CategoryReportScreen(
        uiState = categoryReportViewModels.uiState.value,
        onSelectCategory = onSelectCategory
    )
}

@Composable
fun CategoryReportScreen(
    uiState: CategoryReportUiState,
    onSelectCategory: (Long) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.category_report),
                navigationIcon = {
                    Spacer(modifier = Modifier.size(48.dp))
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.caret_down),
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "caret down"
                        )
                    }
                }
            )
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
                listCategoryUi = uiState.expenseList,
                title = stringResource(id = R.string.expense),
                onClickItem = onSelectCategory,
            )
            Spacer(modifier = Modifier.height(40.dp))
            MonoCategorySurface(
                listCategoryUi = uiState.incomeList,
                title = stringResource(id = R.string.income),
                onClickItem = onSelectCategory,
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryReportScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryReportScreen() {
    MonoApplicationTheme {
        CategoryReportScreen(
            uiState = CategoryReportUiState()
        )


    }
}
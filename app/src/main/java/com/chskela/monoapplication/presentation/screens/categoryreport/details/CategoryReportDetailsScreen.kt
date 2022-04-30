package com.chskela.monoapplication.presentation.screens.categoryreport.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.presentation.screens.categoryreport.details.components.DetailsBigIcon
import com.chskela.monoapplication.presentation.screens.categoryreport.details.components.DetailsTabs
import com.chskela.monoapplication.presentation.screens.categoryreport.details.components.ReportChart
import com.chskela.monoapplication.presentation.screens.categoryreport.details.models.CategoryReportDetailsUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.components.transactionList.MonoTransactionList
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryReportDetailsActivityScreen(
    categoryReportDetailsViewModels: CategoryReportDetailsViewModels = hiltViewModel(),
    onBack: () -> Unit,
    categoryId: Long,
) {
    LaunchedEffect(true) {
//        categoryReportDetailsViewModels.onEvent(CategoryReportDetailsEvent.GetCategory(categoryId))
        categoryReportDetailsViewModels.onEvent(CategoryReportDetailsEvent.GetCategory(1))
    }

    CategoryReportDetailsScreen(
        uiState = categoryReportDetailsViewModels.uiState.value,
        onEvent = categoryReportDetailsViewModels::onEvent,
        onBack = onBack
    )
}

@Composable
fun CategoryReportDetailsScreen(
    uiState: CategoryReportDetailsUiState,
    onEvent: (CategoryReportDetailsEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val color = when(uiState.typeCategory) {
        TypeCategory.Expense -> Expense
        TypeCategory.Income -> Income
    }

    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.category_report),
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.caret_down),
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "caret down"
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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            DetailsTabs(
                state = uiState.currentTab,
                onSelect = { onEvent(CategoryReportDetailsEvent.SelectTab(it)) }
            )

            Spacer(modifier = Modifier.height(40.dp))

            DetailsBigIcon(
                title = uiState.categoryName,
                icon = uiState.icon,
                color = color
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "This month",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$871,81",
                style = MaterialTheme.typography.h1,
                color = color
            )
            ReportChart(graphColor = color)

            MonoTransactionList(transactionList =  uiState.transactionList, currency = uiState.currency)
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryReportDetailsScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryReportDetailsScreen() {
    MonoApplicationTheme {
        CategoryReportDetailsScreen(
            uiState = CategoryReportDetailsUiState()
        )
    }
}
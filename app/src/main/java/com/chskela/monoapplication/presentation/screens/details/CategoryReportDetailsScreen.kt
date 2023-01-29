package com.chskela.monoapplication.presentation.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.presentation.screens.details.components.DetailsBigIcon
import com.chskela.monoapplication.presentation.screens.details.components.ReportChart
import com.chskela.monoapplication.presentation.screens.details.components.ReportUi
import com.chskela.monoapplication.presentation.screens.details.models.CategoryReportDetailsUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.components.transactionList.MonoTransactionList
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TypeTransaction
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryReportDetailsScreen(
    uiState: CategoryReportDetailsUiState,
    onEvent: (CategoryReportDetailsEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val color = when (uiState.typeCategory) {
        TypeCategory.Expense -> Expense
        TypeCategory.Income -> Income
    }

    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.category_report),
                onNavigation = onBack
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        BoxWithConstraints(modifier = Modifier.padding(padding)) {
            val heightScreen = maxHeight

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (tabs, icon, column, chart, list) = createRefs()
                val heightIcon = heightScreen * 0.3f
                val heightChart = heightScreen * 0.2f
                val heightColumn = 48.dp
                val margin8 = 8.dp
                val margin16 = 16.dp
                val sumMargin = margin8 * 2 + margin16 * 2
                val heightList =
                    heightScreen - (heightIcon + heightChart + sumMargin + heightColumn)
//                DetailsTabs(
//                    modifier = Modifier.constrainAs(tabs) {
//                        top.linkTo(parent.top, margin = 16.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    },
//                    state = uiState.currentTab,
//                    onSelect = { onEvent(CategoryReportDetailsEvent.SelectTab(it)) }
//                )

                DetailsBigIcon(
                    modifier = Modifier
                        .size(size = heightIcon)
                        .constrainAs(ref = icon) {
                            top.linkTo(anchor = parent.top, margin = margin16)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
                        },
                    title = uiState.categoryName,
                    icon = uiState.icon,
                    color = color
                )

                Column(
                    modifier = Modifier.constrainAs(column) {
                        top.linkTo(icon.bottom, margin = margin16)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.this_month),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${uiState.currency}${uiState.sumThisMonth}",
                        style = MaterialTheme.typography.displayLarge,
                        color = color
                    )
                }

                ReportChart(
                    modifier = Modifier
                        .height(height = heightChart)
                        .constrainAs(ref = chart) {
                            top.linkTo(anchor = column.bottom, margin = margin8)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
                        },
                    graphColor = color,
                    reportsList = uiState.reportsList
                )

                MonoTransactionList(
                    modifier = Modifier
                        .height(height = heightList)
                        .constrainAs(ref = list) {
                            top.linkTo(anchor = chart.bottom, margin = margin8)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
                            bottom.linkTo(anchor = parent.bottom)
                        },
                    transactionList = uiState.transactionList,
                    currency = uiState.currency
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryReportDetailsScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryReportDetailsScreen() {
    MonoApplicationTheme {
        CategoryReportDetailsScreen(
            uiState = CategoryReportDetailsUiState(
                transactionList = List(10) {
                    TransactionListUi(
                        amount = 10.0 * Random.nextInt(1, 20),
                        note = "Note dfhgdfgdffgdfg",
                        type = if (it % 2 == 0) TypeTransaction.Expense else TypeTransaction.Income,
                        category = "Food",
                        icon = "category_food"
                    )
                }, reportsList = listOf(
                    ReportUi(signatures = "Oct", amount = 2),
                    ReportUi(signatures = "Nov", amount = 10),
                    ReportUi(signatures = "Dec", amount = 4),
                    ReportUi(signatures = "Oct", amount = 1),
                    ReportUi(signatures = "Nov", amount = 14),
                    ReportUi(signatures = "Dec", amount = 7)
                )
            )
        )
    }
}
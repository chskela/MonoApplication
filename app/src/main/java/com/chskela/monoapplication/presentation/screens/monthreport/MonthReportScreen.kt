package com.chskela.monoapplication.presentation.screens.monthreport

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.monthreport.components.BorderRow
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.monthreport.models.TypeTransaction
import com.chskela.monoapplication.presentation.ui.components.datarange.MonoDateRange
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.components.transactionList.MonoTransactionList
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import kotlin.math.absoluteValue


@Composable
fun MonthReportActivityScreen(
    monthReportViewModels: MonthReportViewModels = hiltViewModel(),
) {
    MonthReportScreen(
        uiState = monthReportViewModels.uiState.value,
        onEvent = monthReportViewModels::onEvent,
    )
}

@Composable
fun MonthReportScreen(
    uiState: MonthReportUiState,
    onEvent: (MonthReportEvent) -> Unit = {},
) {
    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.month_report),
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
    ) { padding ->
        val income = stringResource(id = R.string.income)
        val expense = stringResource(id = R.string.expense)
        val currency = uiState.currency
        fun sign(value: Double) = if (value >= 0) "+" else "-"
        Column(modifier = Modifier.padding(padding)) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column {
                    Spacer(modifier = Modifier.size(24.dp))
                    MonoDateRange(
                        currentDate = uiState.currentData,
                        onPrevious = { onEvent(MonthReportEvent.PreviousMonth) },
                        onNext = { onEvent(MonthReportEvent.NextMonth) })
                    Spacer(modifier = Modifier.size(24.dp))

                    BorderRow(content = listOf {
                        Text(
                            text = stringResource(id = R.string.current_balance)
                        )
                        Text(
                            text = "${sign(uiState.currentBalance)}$currency${uiState.currentBalance.absoluteValue}",
                            color = MaterialTheme.colors.primary
                        )
                    })
                    Spacer(modifier = Modifier.size(16.dp))

                    BorderRow(content = listOf(
                        {
                            Text(text = income)
                            Text(text = "$currency${uiState.income}")
                        },
                        { Spacer(modifier = Modifier.size(12.dp)) },
                        {
                            Text(text = expense)
                            Text(text = "-$currency${uiState.expense}")
                        }
                    ))
                    Spacer(modifier = Modifier.size(16.dp))

                    BorderRow(content = listOf(
                        {
                            Text(
                                text = "$expense/$income"
                            )
                            Text(text = "${sign(uiState.expenseIncome)}$currency${uiState.expenseIncome.absoluteValue}")
                        },
                        { Spacer(modifier = Modifier.size(12.dp)) },
                        {
                            Text(text = stringResource(id = R.string.previous_balance))
                            Text(text = "$currency${uiState.previousBalance}")
                        }
                    ))

                    Spacer(modifier = Modifier.size(24.dp))

                    MonoTabs(
                        state = uiState.currentTab,
                        titles = listOf(
                            stringResource(id = R.string.all),
                            stringResource(id = R.string.expense),
                            stringResource(id = R.string.income)
                        ),
                        onSelect = { onEvent(MonthReportEvent.SelectTab(it)) })
                }
            }
            MonoTransactionList(transactionList = uiState.transactionList, currency = currency)
        }
    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonthReportScreen() {
    MonoApplicationTheme {
        MonthReportScreen(
            uiState = MonthReportUiState(
                currentData = "February, 2022",
                currency = "$",
                currentBalance = 40710.00,
                income = 143100.00,
                expense = 118150.00,
                expenseIncome = 24950.00,
                previousBalance = 15760.00,
                currentTab = 0,
                transactionList = listOf(
                    TransactionUi(
                        id = 0,
                        timestamp = 16165163564,
                        amount = 10,
                        note = "Note dfhgdfgdfdfgdsfdfgsdagfdfdfggdfgdfgdfgdfgdhfddfghhfddfgdfgh",
                        type = TypeTransaction.Expense,
                        category = "Food",
                        icon = "category_food"
                    ),
                    TransactionUi(
                        id = 1,
                        timestamp = 16165165465,
                        amount = 10,
                        note = "Note",
                        type = TypeTransaction.Income,
                        category = "Pay",
                        icon = "category_baby"
                    )
                )
            )
        )
    }
}
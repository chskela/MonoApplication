package com.chskela.monoapplication.presentation.screens.reports.components.monthreport

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.reports.components.monthreport.components.BorderRow
import com.chskela.monoapplication.presentation.screens.reports.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.reports.models.TypeTransaction
import com.chskela.monoapplication.presentation.screens.reports.ReportsEvent
import com.chskela.monoapplication.presentation.ui.components.datarange.MonoDateRange
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.transactionList.MonoTransactionList
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import kotlin.math.absoluteValue

@Composable
fun MonthReportScreen(
    currentData: String = "February, 2022",
    currency: String = "",
    currentBalance: Double = 0.0,
    income: Double = 0.0,
    expense: Double = 0.0,
    expenseIncome: Double = 0.0,
    previousBalance: Double = 0.0,
    currentTab: Int = 0,
    transactionList: List<TransactionUi> = emptyList(),
    onEvent: (ReportsEvent) -> Unit = {},
) {
    val incomeStr = stringResource(id = R.string.income)
    val expenseStr = stringResource(id = R.string.expense)
    fun sign(value: Double) = if (value >= 0) "+" else "-"

    Column {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column {

                MonoDateRange(
                    currentDate = currentData,
                    onPrevious = { onEvent(ReportsEvent.PreviousMonth) },
                    onNext = { onEvent(ReportsEvent.NextMonth) })

                Spacer(modifier = Modifier.size(24.dp))

                BorderRow(content = listOf {
                    Text(
                        text = stringResource(id = R.string.current_balance),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "${sign(currentBalance)}$currency${currentBalance.absoluteValue}",
                        color = if (currentBalance >= 0) Income else Expense
                    )
                })

                Spacer(modifier = Modifier.size(16.dp))

                BorderRow(content = listOf(
                    {
                        Text(
                            text = incomeStr,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = "$currency$income")
                    },
                    { Spacer(modifier = Modifier.size(12.dp)) },
                    {
                        Text(
                            text = expenseStr,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = "-$currency$expense")
                    }
                ))

                Spacer(modifier = Modifier.size(16.dp))

                BorderRow(content = listOf(
                    {
                        Text(
                            text = "$expenseStr/$incomeStr",
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = "${sign(expenseIncome)}$currency${expenseIncome.absoluteValue}")
                    },
                    { Spacer(modifier = Modifier.size(12.dp)) },
                    {
                        Text(
                            text = stringResource(id = R.string.previous_balance),
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = "$currency${previousBalance}")
                    }
                ))

                Spacer(modifier = Modifier.size(24.dp))

                MonoTabs(
                    state = currentTab,
                    titles = listOf(
                        stringResource(id = R.string.all),
                        stringResource(id = R.string.expense),
                        stringResource(id = R.string.income)
                    ),
                    onSelect = { onEvent(ReportsEvent.SelectTab(it)) })
            }
        }
        MonoTransactionList(transactionList = transactionList, currency = currency)
    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonthReportScreen() {
    MonoApplicationTheme {
        MonthReportScreen(
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
                    amount = 10.0,
                    note = "Note dfhgdfgdfdfgdsfdfgsdagfdfdfggdfgdfgdfgdfgdhfddfghhfddfgdfgh",
                    type = TypeTransaction.Expense,
                    category = "Food",
                    icon = "category_food"
                ),
                TransactionUi(
                    id = 1,
                    timestamp = 16165165465,
                    amount = 10.0,
                    note = "Note",
                    type = TypeTransaction.Income,
                    category = "Pay",
                    icon = "category_baby"
                )
            )
        )
    }
}
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
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TypeTransaction
import com.chskela.monoapplication.presentation.screens.reports.ReportsEvent
import com.chskela.monoapplication.presentation.ui.components.datarange.MonoDateRange
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.transactionList.MonoTransactionList
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import kotlin.random.Random

@Composable
fun MonthReportScreen(
    currentData: String = "February, 2022",
    currentBalance: String = "0.00",
    income: String = "0.00",
    expense: String = "0.00",
    expenseIncome: String = "0.00",
    previousBalance: String = "0.00",
    currentTab: Int = 0,
    transactionList: List<TransactionListUi> = emptyList(),
    onEvent: (ReportsEvent) -> Unit = {},
) {
    val incomeStr = stringResource(id = R.string.income)
    val expenseStr = stringResource(id = R.string.expense)
    val currentBalanceColor = if (currentBalance.startsWith('-')) Expense else Income

    Column {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column {

                MonoDateRange(
                    currentDate = currentData,
                    onPrevious = { onEvent(ReportsEvent.PreviousMonth) },
                    onNext = { onEvent(ReportsEvent.NextMonth) }
                )

                Spacer(modifier = Modifier.size(24.dp))

                BorderRow(content = listOf {
                    Text(
                        text = stringResource(id = R.string.current_balance),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = currentBalance,
                        color = currentBalanceColor
                    )
                })

                Spacer(modifier = Modifier.size(16.dp))

                BorderRow(content = listOf(
                    {
                        Text(
                            text = incomeStr,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = income)
                    },
                    { Spacer(modifier = Modifier.size(12.dp)) },
                    {
                        Text(
                            text = expenseStr,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = "-$expense")
                    }
                ))

                Spacer(modifier = Modifier.size(16.dp))

                BorderRow(content = listOf(
                    {
                        Text(
                            text = "$expenseStr/$incomeStr",
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = expenseIncome)
                    },
                    { Spacer(modifier = Modifier.size(12.dp)) },
                    {
                        Text(
                            text = stringResource(id = R.string.previous_balance),
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = previousBalance)
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
        MonoTransactionList(transactionList = transactionList)
    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonthReportScreen() {
    MonoApplicationTheme {
        MonthReportScreen(
            currentData = "February, 2022",
            currentBalance = "40710.00 $",
            income = "143100.00 $",
            expense = "118150.00 $",
            expenseIncome = "24950.00 $",
            previousBalance = "15760.00 $",
            currentTab = 0,
            transactionList = List(10){
                TransactionListUi(
                    amount = "${10.0 * Random.nextInt(1, 20)} $",
                    note = "Note dfhgdfgdffgdfg",
                    type = if (it % 2 == 0) TypeTransaction.Expense else TypeTransaction.Income,
                    category = "Food",
                    icon = "category_food"
                )
            }
        )
    }
}
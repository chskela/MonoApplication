package com.chskela.monoapplication.presentation.screens.monthreport

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.monthreport.components.BorderRow
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.monthreport.models.TypeTransaction
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.datarange.MonoDateRange
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme


@Composable
fun MonthReportActivityScreen(
    monthReportViewModels: MonthReportViewModels = hiltViewModel(),
    bottomBar: @Composable () -> Unit = {},
) {
    MonthReportScreen(
        uiState = monthReportViewModels.uiState.value,
        onEvent = monthReportViewModels::onEvent,
        bottomBar = bottomBar
    )
}

@Composable
fun MonthReportScreen(
    uiState: MonthReportUiState,
    onEvent: (MonthReportEvent) -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
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
        bottomBar = bottomBar,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        val income = stringResource(id = R.string.income)
        val expense = stringResource(id = R.string.expense)
        val currency = uiState.currency
        Column {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column {
                    Spacer(modifier = Modifier.size(24.dp))
                    MonoDateRange(
                        currentDate = uiState.currentData,
                        onPrevious = { onEvent(MonthReportEvent.PreviousMonth) },
                        onNext = { onEvent(MonthReportEvent.NextMonth) })
                    Spacer(modifier = Modifier.size(24.dp))

                    BorderRow(content = listOf {
                        Text(text = stringResource(id = R.string.current_balance))
                        Text(text = "$currency${uiState.currentBalance}")
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
                            Text(text = "$currency${uiState.expenseIncome}")
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

            Row {
                LazyColumn(modifier = Modifier.padding(vertical = 16.dp)) {
                    items(items = uiState.transactionList) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(0.7f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = it.icon),
                                        contentDescription = it.category
                                    )
                                    Spacer(modifier = Modifier.size(12.dp))
                                    Text(
                                        text = it.category,
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.onSurface,
                                    )
                                    if (it.note.isNotBlank()) {
                                        Text(
                                            text = " (${it.note})",
                                            style = MaterialTheme.typography.caption,
                                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(0.3f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                val prefix = when (it.type) {
                                    TypeTransaction.Expense -> "-"
                                    TypeTransaction.Income -> "+"
                                }
                                val color = when (it.type) {
                                    TypeTransaction.Expense -> Expense
                                    TypeTransaction.Income -> Income
                                }

                                Text(
                                    text = "$prefix$currency${it.amount}",
                                    style = MaterialTheme.typography.body1,
                                    color = color
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
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
                        icon = R.drawable.category_food
                    ),
                    TransactionUi(
                        id = 1,
                        timestamp = 16165165465,
                        amount = 10,
                        note = "Note",
                        type = TypeTransaction.Income,
                        category = "Pay",
                        icon = R.drawable.category_baby
                    )
                )
            )
        )
    }
}
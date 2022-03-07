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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.monthreport.components.BorderRow
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.DateRange
import com.chskela.monoapplication.presentation.ui.components.bottomnavigation.MonoBottomNavigation
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonthReportScreen() {
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
        bottomBar = { MonoBottomNavigation(selectedItem = 2, onClick = { TODO() }) },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        val income = stringResource(id = R.string.income)
        val expense = stringResource(id = R.string.expense)
        Column {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column {
                    Spacer(modifier = Modifier.size(24.dp))
                    DateRange(
                        currentDate = "February, 2022",
                        onPrevious = { /*TODO*/ },
                        onNext = {/*TODO*/ })
                    Spacer(modifier = Modifier.size(24.dp))

                    BorderRow(content = listOf {
                        Text(text = stringResource(id = R.string.current_balance))
                        Text(text = "\$40,710.00")
                    })
                    Spacer(modifier = Modifier.size(16.dp))

                    BorderRow(content = listOf(
                        {
                            Text(text = income)
                            Text(text = "\$40,710.00")
                        },
                        { Spacer(modifier = Modifier.size(12.dp)) },
                        {
                            Text(text = expense)
                            Text(text = "\$40,710.00")
                        }
                    ))
                    Spacer(modifier = Modifier.size(16.dp))

                    BorderRow(content = listOf(
                        {
                            Text(
                                text = "${expense}/${income}"
                            )
                            Text(text = "\$40,710.00")
                        },
                        { Spacer(modifier = Modifier.size(12.dp)) },
                        {
                            Text(text = "Current balance")
                            Text(text = "\$40,710.00")
                        }
                    ))

                    Spacer(modifier = Modifier.size(24.dp))

                    MonoTabs(state = 0, titles = listOf("All", "Expense", "Income"), onSelect = {})
                }
            }

            Row {
                LazyColumn(modifier = Modifier.padding(vertical = 16.dp)) {
                    items(listOf("STRING", "sdfgdsf")) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column() {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.category_books),
                                        contentDescription = "Food"
                                    )
                                    Spacer(modifier = Modifier.size(12.dp))
                                    Text(
                                        text = "Food ",
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.onSurface
                                    )
                                    if (true) Text(
                                        text = "(Pizza for lazyday)",
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                                    )
                                }

                            }
                            Column(verticalArrangement = Arrangement.Center) {
                                Text(
                                    text = "+\$50,00", style = MaterialTheme.typography.body1,
                                    color = Expense
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

        )
    }
}
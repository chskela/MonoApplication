package com.chskela.monoapplication.presentation.ui.components.transactionList

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TypeTransaction
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonoTransactionList(
    modifier: Modifier = Modifier,
    transactionList: List<TransactionListUi>,
    currency: String = ""
) {
    LazyColumn(modifier = modifier.padding(vertical = 16.dp)) {
        items(items = transactionList) { (amount, note, type, category, icon) ->
            val prefix = when (type) {
                TypeTransaction.Expense -> "-"
                TypeTransaction.Income -> "+"
            }
            val color = when (type) {
                TypeTransaction.Expense -> Expense
                TypeTransaction.Income -> Income
            }
            val categoryIcon = iconsMap[icon]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // TODO настроить размер колонок
                Column(modifier = Modifier.weight(0.7f)) {
                    Row(
                        modifier = Modifier.height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        categoryIcon?.let { id ->
                            Icon(
                                imageVector = ImageVector.vectorResource(id = id),
                                contentDescription = category,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            text = category,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1
                        )
                        if (note.isNotBlank()) {
                            Text(
                                text = " (${note})",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.weight(0.4f),
                    horizontalAlignment = Alignment.End
                ) {
                    Box(
                        modifier = Modifier.height(32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        // TODO форматирование amount
                        Text(
                            text = "$prefix${amount} $currency",
                            style = MaterialTheme.typography.bodyLarge,
                            color = color,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light MonoTopAppBar")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoTopAppBar() {
    MonoApplicationTheme {
        MonoTransactionList(
            transactionList = listOf(
                TransactionListUi(
                    amount = 1053345.0,
                    note = "test1sdgsdfgsdgsdfgsdafgsdfgsdfgsdfgsdfgsdfgsdfsdvsfdfvs",
                    type = TypeTransaction.Expense,
                    category = "test",
                    icon = "baby"
                ),
                TransactionListUi(
                    amount = 10.0,
                    note = "test1",
                    type = TypeTransaction.Income,
                    category = "test",
                    icon = "baby"
                ),
            ),
            currency = "$"
        )
    }
}
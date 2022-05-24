package com.chskela.monoapplication.presentation.ui.components.transactionList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.presentation.screens.reports.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.reports.models.TypeTransaction
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income

@Composable
fun MonoTransactionList(
    modifier: Modifier = Modifier,
    transactionList: List<TransactionUi>,
    currency: String = ""
) {
    Row(modifier = modifier) {
        LazyColumn(modifier = Modifier.padding(vertical = 16.dp)) {
            items(items = transactionList) { transactionUi ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(0.7f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val icon = iconsMap[transactionUi.icon]
                            icon?.let { id ->
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = id),
                                    contentDescription = transactionUi.category
                                )
                            }

                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                text = transactionUi.category,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onSurface,
                            )
                            if (transactionUi.note.isNotBlank()) {
                                Text(
                                    text = " (${transactionUi.note})",
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
                        val prefix = when (transactionUi.type) {
                            TypeTransaction.Expense -> "-"
                            TypeTransaction.Income -> "+"
                        }
                        val color = when (transactionUi.type) {
                            TypeTransaction.Expense -> Expense
                            TypeTransaction.Income -> Income
                        }

                        Text(
                            text = "$prefix${currency}${transactionUi.amount}",
                            style = MaterialTheme.typography.body1,
                            color = color
                        )
                    }
                }
            }
        }
    }
}
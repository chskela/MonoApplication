package com.chskela.monoapplication.presentation.screens.reports.components.monthreport.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme3

@Composable
fun BorderRow(
    modifier: Modifier = Modifier,
    content: List<@Composable () -> Unit>
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Column {
            content.map { item ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    item()
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light BorderRow", showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewBorderRow() {
    MonoApplicationTheme3 {
        BorderRow(content = listOf {
            Text(text = "Current balance")
            Text(text = "\$40,710.00")
        }
        )
    }
}
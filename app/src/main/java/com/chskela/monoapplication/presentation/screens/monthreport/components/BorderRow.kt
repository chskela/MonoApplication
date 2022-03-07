package com.chskela.monoapplication.presentation.screens.monthreport.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun BorderRow(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = MaterialTheme.shapes.medium
            )
            .background(color = MaterialTheme.colors.surface, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Column(content = content)
    }
}

@Preview(showBackground = true, name = "Light BorderRow", showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewBorderRow() {
    MonoApplicationTheme {
        BorderRow(content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Current balance")
                Text(text = "\$40,710.00")
            }
        })
    }
}
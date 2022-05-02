package com.chskela.monoapplication.presentation.screens.reports.components.dropchoice

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.reports.models.Report
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun DropChoice(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    items: List<String> = emptyList(),
    state: Report = Report.Month,
    onSelect: (Report) -> Unit = {}
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = shape
            )
            .background(
                color = MaterialTheme.colors.surface,
                shape = shape
            )
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        items.mapIndexed { index, item ->
            val selected = state.info == index
            val color: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onSurface
                }
            )
            Text(
                modifier = Modifier.clickable { onSelect(state) },
                text = item,
                color = color
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true, name = "Light DropChoice", showSystemUi = false)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = false)
@Composable
fun PreviewDropChoice() {
    MonoApplicationTheme {
        DropChoice(
            items = listOf(
                stringResource(id = R.string.monthly),
                stringResource(id = R.string.category)
            )
        )
    }

}
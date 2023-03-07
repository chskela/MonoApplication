package com.chskela.monoapplication.presentation.screens.details.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun DetailsTabs(
    modifier: Modifier = Modifier,
    state: Int,
    onSelect: (Int) -> Unit = {}
) {
    TabRow(
        modifier = modifier
            .heightIn(max = 56.dp)
            .aspectRatio(3.57f)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.small
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
            .padding(4.dp),
        selectedTabIndex = 1,
        indicator = @Composable { },
        divider = {},
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        listOf(
            stringResource(id = R.string.month),
            stringResource(id = R.string.week)
        ).forEachIndexed { index, title ->
            val selected = state == index
            val color: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            val background: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surface
                },
            )
            val shape = if (index == 0) {
                RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
            } else RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)

            Tab(
                modifier = Modifier
                    .background(color = background, shape = shape)
                    .clip(shape = shape),
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                },
                selected = selected,
                onClick = { onSelect(index) }
            )
        }
    }
}

@Preview(showBackground = true, name = "Light DetailsTabs", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailsTabs() {
    MonoApplicationTheme {
        DetailsTabs(state = 0)
    }
}
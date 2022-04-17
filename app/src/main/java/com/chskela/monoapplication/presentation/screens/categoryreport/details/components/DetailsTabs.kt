package com.chskela.monoapplication.presentation.screens.categoryreport.details.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
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
    state: Int,
    onSelect: (Int) -> Unit = {}
) {
    TabRow(
        modifier = Modifier
            .size(height = 48.dp, width = 199.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = MaterialTheme.shapes.small
            )
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.small
            )
            .padding(4.dp),
        selectedTabIndex = 1,
        indicator = @Composable { },
        divider = {},
        backgroundColor = MaterialTheme.colors.surface
    ) {
        listOf(
            stringResource(id = R.string.month),
            stringResource(id = R.string.week)
        ).forEachIndexed { index, title ->
            val selected = state == index
            val color: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colors.onSurface
                } else {
                    MaterialTheme.colors.secondary
                }
            )
            val background: Color by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colors.background
                } else {
                    MaterialTheme.colors.surface
                },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            val shape = if (index == 0) {
                RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
            } else RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)

            Tab(
                modifier = Modifier
                    .background(
                        color = background,
                        shape = shape
                    )
                    .clip(shape = shape),
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body1,
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
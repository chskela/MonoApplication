package com.chskela.monoapplication.presentation.ui.components.topappbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonoTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {
        Spacer(
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
                .size(48.dp)
        )
    },
    onNavigation: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = onNavigation) {
            Text(
                text = "Back",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    },
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )
}

@Preview(showBackground = true, name = "Light MonoTopAppBar")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoTopAppBar() {
    MonoApplicationTheme {
        MonoTopAppBar(title = "Currency")
    }
}

@Preview(showBackground = true, name = "Light MonoTopAppBar no Back")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoTopAppBarNoBack() {
    MonoApplicationTheme {
        MonoTopAppBar(title = "Currency", navigationIcon = {
            Spacer(modifier = Modifier.size(48.dp))
        })
    }
}
package com.chskela.monoapplication.presentation.ui.components.topappbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
    onNavigation: () -> Unit = {},
    isBack: Boolean = true,
) {
    TopAppBar(
        title = {
            Text(text = title,
                style = MaterialTheme.typography.body1)
        },
        modifier = modifier,
        navigationIcon = {
            if (isBack) {
                IconButton(onClick = onNavigation) {
                    Text(text = "Back",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(start = 12.dp))
//                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }

        },

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
        MonoTopAppBar(title = "Currency", isBack = false)
    }
}
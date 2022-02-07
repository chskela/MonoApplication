package com.chskela.monoapplication.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.ui.theme.Gray
import com.chskela.monoapplication.ui.theme.GrayDark
import com.chskela.monoapplication.ui.theme.MonoApplicationTheme

@Composable
fun MonoButton(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    isPrimary: Boolean = true,
    onClick: () -> Unit = {},
    text: String? = null,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
) {
    Button(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPrimary) MaterialTheme.colors.primary else MaterialTheme.colors.error,
            disabledBackgroundColor = if (darkTheme) GrayDark else Gray,
            disabledContentColor = MaterialTheme.colors.secondary
        )
    ) {
        text?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body1,
            )
        } ?: content.invoke(this)
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButton() {
    MonoApplicationTheme() {
        Surface() {
            MonoButton(text = "Submit")
        }

    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButtonError() {
    MonoApplicationTheme() {
        Surface() {
            MonoButton(text = "Submit", isPrimary = false)
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButtonDisable() {
    MonoApplicationTheme() {
        Surface() {
            MonoButton(text = "Submit", enabled = false)
        }
    }
}
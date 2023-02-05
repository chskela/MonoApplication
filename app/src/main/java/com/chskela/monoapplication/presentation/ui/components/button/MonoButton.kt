package com.chskela.monoapplication.presentation.ui.components.button

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonoButton(
    modifier: Modifier = Modifier,
    isPrimary: Boolean = true,
    onClick: () -> Unit = {},
    text: String? = null,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
) {
    val (containerColor, contentColor) = if (isPrimary) {
        MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
    } else MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        )
    ) {
        text?.let {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = it,
                style = MaterialTheme.typography.bodyLarge,
            )
        } ?: content.invoke(this)
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButton() {
    MonoApplicationTheme {
        Surface {
            MonoButton(text = "Submit")
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButtonError() {
    MonoApplicationTheme {
        Surface {
            MonoButton(text = "Submit", isPrimary = false)
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButtonDisable() {
    MonoApplicationTheme {
        Surface {
            MonoButton(text = "Submit", enabled = false)
        }
    }
}
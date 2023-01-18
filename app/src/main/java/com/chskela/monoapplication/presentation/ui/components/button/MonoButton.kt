package com.chskela.monoapplication.presentation.ui.components.button

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme3

@Composable
fun MonoButton(
    modifier: Modifier = Modifier,
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
            containerColor = if (isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        text?.let {
            Text(
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
    MonoApplicationTheme3 {
        Surface {
            MonoButton(text = "Submit")
        }

    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButtonError() {
    MonoApplicationTheme3 {
        Surface {
            MonoButton(text = "Submit", isPrimary = false)
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButtonDisable() {
    MonoApplicationTheme3 {
        Surface {
            MonoButton(text = "Submit", enabled = false)
        }
    }
}
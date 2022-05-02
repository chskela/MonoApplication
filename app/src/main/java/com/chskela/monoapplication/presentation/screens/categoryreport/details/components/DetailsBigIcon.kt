package com.chskela.monoapplication.presentation.screens.categoryreport.details.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun DetailsBigIcon(
    title: String,
    icon: String = "",
    color: Color,
) {
    Box(
        modifier = Modifier
            .size(312.dp)
            .background(
                color = color.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                modifier = Modifier.size(200.dp),
                painter = painterResource(
                    id = iconsMap.getOrDefault(icon, R.drawable.category_bank)
                ),
                contentDescription = "",
                tint = color
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Preview(showBackground = true, name = "Light DetailsBigIcon", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailsBigIcon() {
    MonoApplicationTheme {
        DetailsBigIcon(title = "Title", color = Expense)
    }
}
package com.chskela.monoapplication.presentation.screens.details.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    modifier: Modifier = Modifier,
    title: String,
    icon: String = "",
    color: Color,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(
                color = color.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 16.dp, horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                modifier = Modifier
                    .weight(85f)
                    .fillMaxSize(),
                painter = painterResource(
                    id = iconsMap.getOrDefault(icon, R.drawable.category_bank)
                ),
                contentDescription = "",
                tint = color
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.weight(15f),
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true, name = "Light DetailsBigIcon", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailsBigIcon() {
    MonoApplicationTheme {
        DetailsBigIcon(modifier = Modifier.size(size = 300.dp), title = "Title", color = Expense)
    }
}
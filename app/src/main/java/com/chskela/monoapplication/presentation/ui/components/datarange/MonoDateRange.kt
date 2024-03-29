package com.chskela.monoapplication.presentation.ui.components.datarange

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MonoDateRange(
    modifier: Modifier = Modifier,
    currentDate: String,
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
            contentDescription = stringResource(id = R.string.previous_day),
            modifier = Modifier
                .clip(shape = CircleShape)
                .clickable { onPrevious() },
            tint = MaterialTheme.colorScheme.onPrimary
        )
        AnimatedContent(
            targetState = currentDate,
//            transitionSpec = {
//                if (targetState.value.state > initialState.value.state) {
//                    slideInHorizontally { width -> width / 3 } + fadeIn() with
//                            slideOutHorizontally { width -> -width / 3 } + fadeOut()
//                } else {
//                    slideInHorizontally { width -> -width / 3 } + fadeIn() with
//                            slideOutHorizontally { width -> width / 3 } + fadeOut()
//                }.using(
//                    SizeTransform(clip = false)
//                )
//            }
        ) { currentDate ->
            Text(
                text = currentDate,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right),
            contentDescription = stringResource(id = R.string.next_day),
            modifier = Modifier
                .clip(shape = CircleShape)
                .clickable { onNext() },
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryItem() {
    MonoApplicationTheme {
        MonoDateRange(currentDate = "January, 2023")
    }
}
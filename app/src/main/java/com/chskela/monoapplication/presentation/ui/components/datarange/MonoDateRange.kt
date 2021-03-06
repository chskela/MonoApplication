package com.chskela.monoapplication.presentation.ui.components.datarange

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MonoDateRange(currentDate: String, onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(modifier = Modifier
        .height(48.dp)
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small
        )
        .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
            contentDescription = stringResource(id = R.string.previous_day),
            modifier = Modifier.clip(shape = CircleShape).clickable { onPrevious() }
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
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right),
            contentDescription = stringResource(id = R.string.next_day),
            modifier = Modifier.clip(shape = CircleShape).clickable { onNext() }
        )
    }
}
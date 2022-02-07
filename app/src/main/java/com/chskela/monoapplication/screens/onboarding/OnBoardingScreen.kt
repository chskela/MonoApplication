package com.chskela.monoapplication.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.ui.components.MonoButton
import com.chskela.monoapplication.ui.theme.MonoApplicationTheme

@Composable
fun OnBoardingScreen() {
    Surface {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "1/3",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondaryVariant)
                    OutlinedButton(onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(horizontal = 24.dp)) {
                        Text(text = "Skip",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondaryVariant)
                    }
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.475f)
                        .padding(bottom = 24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.on_boarding_step1),
                    contentDescription = "",
                )
                Text(
                    text = stringResource(R.string.on_boarding_title_step1),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Text(text = stringResource(id = R.string.on_boarding_body_step1),
                    style = MaterialTheme.typography.body1)
            }
            MonoButton(text = stringResource(id = R.string.on_boarding_continue))
        }
    }
}

@Preview(showBackground = true, name = "Light", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewOnBoardingScreen() {
    MonoApplicationTheme {
        OnBoardingScreen()
    }
}
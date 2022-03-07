package com.chskela.monoapplication.presentation.screens.onboarding

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.onboarding.models.OnBoardingPage
import com.chskela.monoapplication.presentation.screens.onboarding.models.OnBoardingUiState
import com.chskela.monoapplication.presentation.screens.onboarding.models.Pages
import com.chskela.monoapplication.presentation.ui.components.button.MonoButton
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun OnBoardingActivityScreen(onBoardingViewModel: OnBoardingViewModel = hiltViewModel()) {
        val uiState = onBoardingViewModel.uiState.value
        val page = uiState.onBoardingPage.page
        OnBoardingScreen(
            uiState = uiState,
            onClickButton = {
                onBoardingViewModel.onEvent(
                    OnBoardingEvent.NextPage(page)
                )
            }
        )
}

@Composable
fun OnBoardingScreen(
    uiState: OnBoardingUiState,
    onSkip: () -> Unit = {},
    onClickButton: () -> Unit = {},
) {
    val (page, image, title, body, buttonTitle) = uiState.onBoardingPage

    Surface {
        Log.e("RESULT", "Screen")
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
                    Text(text = "${page.pageNumber}/3",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface)

                    if (uiState.skip) {
                        OutlinedButton(onClick = onSkip,
                            contentPadding = PaddingValues(horizontal = 24.dp)) {
                            Text(text = "Skip",
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onSurface)
                        }
                    }
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.512f)
                        .padding(bottom = 24.dp),
                    imageVector = ImageVector.vectorResource(id = image),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onSurface
                )
                Text(text = stringResource(id = body),
                    style = MaterialTheme.typography.body1)
            }
            MonoButton(text = stringResource(id = buttonTitle), onClick = onClickButton)
        }
    }
}

@Preview(showBackground = true, name = "Light OnBoardingScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewOnBoardingScreen() {
    MonoApplicationTheme {
        OnBoardingScreen(uiState = OnBoardingUiState(
            onBoardingPage = OnBoardingPage(
                page = Pages.First,
                image = R.drawable.on_boarding_step1,
                title = R.string.on_boarding_title_step1,
                body = R.string.on_boarding_body_step1,
                buttonTitle = R.string.on_boarding_continue,
            ),
            skip = true
        ))
    }
}
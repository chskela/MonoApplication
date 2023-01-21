package com.chskela.monoapplication.presentation.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.onboarding.models.OnBoardingPage
import com.chskela.monoapplication.presentation.screens.onboarding.models.OnBoardingUiState
import com.chskela.monoapplication.presentation.screens.onboarding.models.Pages
import com.chskela.monoapplication.presentation.ui.components.button.MonoButton
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun OnBoardingActivityScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
    onMainScreen: () -> Unit = {}
) {
    val uiState = onBoardingViewModel.uiState.value
    OnBoardingScreen(
        uiState = uiState,
        onEvent = onBoardingViewModel::onEvent,
        onMainScreen = onMainScreen
    )
}

@Composable
fun OnBoardingScreen(
    uiState: OnBoardingUiState,
    onEvent: (OnBoardingEvent) -> Unit = {},
    onMainScreen: () -> Unit = {}
) {
    val (page, image, title, body, buttonTitle) = uiState.onBoardingPage

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${page.pageNumber}/3",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (uiState.skip) {
                        OutlinedButton(
                            onClick = {
                                onMainScreen()
                                onEvent(OnBoardingEvent.Skip)
                            },
                            contentPadding = PaddingValues(horizontal = 24.dp)
                        ) {
                            Text(
                                text = "Skip",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
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
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(id = body),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            MonoButton(
                text = stringResource(id = buttonTitle),
                onClick = {
                    when (page) {
                        Pages.Third -> {
                            onMainScreen()
                            onEvent(OnBoardingEvent.NextPage(Pages.Third))
                        }
                        else -> onEvent(OnBoardingEvent.NextPage(page))
                    }
                })
        }
    }
}

@Preview(showBackground = true, name = "Light OnBoardingScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewOnBoardingScreen() {
    MonoApplicationTheme {
        OnBoardingScreen(
            uiState = OnBoardingUiState(
                onBoardingPage = OnBoardingPage(
                    page = Pages.First,
                    image = R.drawable.on_boarding_step1,
                    title = R.string.on_boarding_title_step1,
                    body = R.string.on_boarding_body_step1,
                    buttonTitle = R.string.on_boarding_continue,
                ),
                skip = true
            )
        )
    }
}
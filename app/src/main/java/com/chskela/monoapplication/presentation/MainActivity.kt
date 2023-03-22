package com.chskela.monoapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !mainActivityViewModel.isLoading.value
        }

        setContent {
            val isDark = isSystemInDarkTheme()
            LaunchedEffect(true) {
                mainActivityViewModel.setTheme(isDark)
            }

            MonoApp(
                onBoardingIsSkip = mainActivityViewModel.onBoardingIsSkip.value,
                darkTheme = isAppDarkTheme(mainActivityViewModel.isDarkTheme)
            )
        }
    }
}


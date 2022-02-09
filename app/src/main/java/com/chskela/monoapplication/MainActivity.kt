package com.chskela.monoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chskela.monoapplication.screens.onboarding.OnBoardingActivityScreen
import com.chskela.monoapplication.ui.theme.MonoApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonoApplicationTheme {
                // A surface container using the 'background' color from the theme
                    OnBoardingActivityScreen()
            }
        }
    }
}
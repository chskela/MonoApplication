package com.chskela.monoapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chskela.monoapplication.presentation.screens.category.CategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.currency.CurrencyActivityScreen
import com.chskela.monoapplication.presentation.screens.monthreport.MonthReportActivityScreen
import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingActivityScreen
import com.chskela.monoapplication.presentation.screens.transaction.TransactionActivityScreen
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonoApplicationTheme {
                MonthReportActivityScreen()
//                TransactionActivityScreen()
//                CurrencyActivityScreen()
//                CategoryActivityScreen()
//                    OnBoardingActivityScreen()
            }
        }
    }
}
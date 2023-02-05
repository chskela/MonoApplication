package com.chskela.monoapplication.presentation.screens.onboarding

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.chskela.monoapplication.R
import com.chskela.monoapplication.core.util.TestTags
import com.chskela.monoapplication.di.TestDataModule
import com.chskela.monoapplication.di.TestDomainModule
import com.chskela.monoapplication.presentation.MainActivity
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(TestDataModule::class, TestDomainModule::class)
class OnBoardingScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val ctx: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            MonoApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = MonoScreens.OnBoarding.name
                ) {
                    composable(MonoScreens.OnBoarding.name) {
                        val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                        OnBoardingScreen(
                            uiState = onBoardingViewModel.uiState,
                            onEvent = onBoardingViewModel::onEvent,
                            onMainScreen = {
                                navController.popBackStack()
                                navController.navigate(MonoScreens.Transaction.name)
                            }
                        )
                    }
                }
            }
        }
    }

    @Test
    fun firstPage_pageNumber_isVisible() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_PAGE_NUMBER)
        node.assertIsDisplayed()
    }

    @Test
    fun firstPage_pageNumber_hasText() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_PAGE_NUMBER)
        node.assertTextEquals("1/3")
    }

    @Test
    fun firstPage_skipButton_isVisible() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertIsDisplayed()
    }

    @Test
    fun firstPage_skipButton_hasText() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertTextEquals(ctx.getString(R.string.skip))
    }

    @Test
    fun firstPage_skipButton_clickAble() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertHasClickAction()
    }

    @Test
    fun firstPage_continueButton_isVisible() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertIsDisplayed()
    }

    @Test
    fun firstPage_continueButton_hasText() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertTextEquals(ctx.getString(R.string.on_boarding_continue))
    }

    @Test
    fun firstPage_continueButton_clickAble() {
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertHasClickAction()
    }
}
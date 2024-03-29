package com.chskela.monoapplication.presentation.screens.onboarding

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.chskela.monoapplication.R
import com.chskela.monoapplication.core.util.TestTags
import com.chskela.monoapplication.di.TestDataModule
import com.chskela.monoapplication.di.domain.*
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
@UninstallModules(
    TestDataModule::class,
    TestCategoryDomainModule::class,
    TestCommonDomainModule::class,
    TestCurrencyDomainModule::class,
    TestOnBoardingDomainModule::class,
    TestReportsDomainModule::class,
    TestTransactionDomainModule::class
)
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

    @Test
    fun secondPage_pageNumber_isVisible() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_PAGE_NUMBER)
        node.assertIsDisplayed()
    }

    @Test
    fun secondPage_pageNumber_hasText() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_PAGE_NUMBER)
        node.assertTextEquals("2/3")
    }

    @Test
    fun secondPage_skipButton_isVisible() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertIsDisplayed()
    }

    @Test
    fun secondPage_skipButton_hasText() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertTextEquals(ctx.getString(R.string.skip))
    }

    @Test
    fun secondPage_skipButton_clickAble() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertHasClickAction()
    }

    @Test
    fun secondPage_continueButton_isVisible() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertIsDisplayed()
    }

    @Test
    fun secondPage_continueButton_hasText() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertTextEquals(ctx.getString(R.string.on_boarding_continue))
    }

    @Test
    fun secondPage_continueButton_clickAble() {
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertHasClickAction()
    }

    @Test
    fun thirdPage_pageNumber_isVisible() {
        nextPage()
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_PAGE_NUMBER)
        node.assertIsDisplayed()
    }

    @Test
    fun thirdPage_pageNumber_hasText() {
        nextPage()
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_PAGE_NUMBER)
        node.assertTextEquals("3/3")
    }

    @Test
    fun thirdPage_skipButton_doesNotExist() {
        nextPage()
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_SKIP_BUTTON)
        node.assertDoesNotExist()
    }

    @Test
    fun thirdPage_getStartedButton_isVisible() {
        nextPage()
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertIsDisplayed()
    }

    @Test
    fun thirdPage_getStartedButton_hasText() {
        nextPage()
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertTextEquals(ctx.getString(R.string.on_boarding_get_started))
    }

    @Test
    fun thirdPage_getStartedButton_clickAble() {
        nextPage()
        nextPage()
        val node = composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON)
        node.assertHasClickAction()
    }

    private fun nextPage() {
        composeRule.onNodeWithTag(TestTags.ON_BOARDING_CONTINUE_BUTTON).performClick()
    }
}
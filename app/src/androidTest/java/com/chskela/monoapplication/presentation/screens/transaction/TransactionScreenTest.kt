package com.chskela.monoapplication.presentation.screens.transaction

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
class TransactionScreenTest {

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
                    startDestination = MonoScreens.Transaction.name
                ) {
                    composable(MonoScreens.Transaction.name) {
                        val transitionViewModel: TransitionViewModel = hiltViewModel()
                        val uiState by transitionViewModel.uiState.collectAsState()
                        TransactionScreen(
                            uiState = uiState,
                            onEvent = transitionViewModel::onEvent,
                        )
                    }
                }
            }
        }
    }

    @Test
    fun tabs_isVisible(){
        composeRule.onNodeWithTag(TestTags.TRANSACTION_TABS).assertIsDisplayed()
    }

    @Test
    fun tabs_isClickAble(){
        val nodes = composeRule.onNodeWithTag(TestTags.TRANSACTION_TABS).onChildren()
        nodes.onFirst().assertHasClickAction()
        nodes.onLast().assertHasClickAction()
    }

    @Test
    fun tabs_hasText(){
        val nodes = composeRule.onNodeWithTag(TestTags.TRANSACTION_TABS).onChildren()
        nodes.onFirst().assertTextContains(ctx.getString(R.string.expense))
        nodes.onLast().assertTextContains(ctx.getString(R.string.income))
    }

    @Test
    fun firstTab_isSelected(){
        composeRule.onNodeWithTag(TestTags.TRANSACTION_TABS)
            .onChildren()
            .onLast()
            .assertIsSelected()
    }

    @Test
    fun amountTextField_isVisible(){
        composeRule.onNodeWithTag(TestTags.TRANSACTION_AMOUNT).assertIsDisplayed()
    }

    @Test
    fun amountTextField_hasTextOnLabel(){
        composeRule.onNodeWithTag(TestTags.TRANSACTION_AMOUNT)
            .onChildren()
            .onFirst()
            .assertTextContains(ctx.getString(R.string.expense))

        composeRule.onNodeWithTag(TestTags.TRANSACTION_TABS)
            .onChildren()
            .onLast()
            .performClick()

        composeRule.onNodeWithTag(TestTags.TRANSACTION_AMOUNT)
            .onChildren()
            .onFirst()
            .assertTextContains(ctx.getString(R.string.income))
    }

    @Test
    fun amountTextField_inputOnlyCurrencyValue(){
        val node = composeRule.onNodeWithTag(TestTags.TRANSACTION_AMOUNT)
            .onChildren()
            .onLast()

        node.performTextInput("test")
        node.assertTextContains("0.00")

        node.performTextInput("+-*/_~\'?><")
        node.assertTextContains("0.00")

        node.performTextInput("12345")
        node.assertTextContains("12345")

        node.performTextInput(".67")
        node.assertTextContains("12345.67")

        node.performTextInput("89")
        node.assertTextContains("12345.67")

        node.performTextInput(".89")
        node.assertTextContains("12345.67")
    }

}
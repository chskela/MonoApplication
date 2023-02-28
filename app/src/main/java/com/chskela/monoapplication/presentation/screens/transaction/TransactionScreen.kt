package com.chskela.monoapplication.presentation.screens.transaction

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.chskela.monoapplication.R
import com.chskela.monoapplication.core.util.TestTags
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.presentation.screens.transaction.models.TransitionUiState
import com.chskela.monoapplication.presentation.ui.components.button.MonoButton
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.datarange.MonoDateRange
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransactionScreen(
    uiState: TransitionUiState,
    onEvent: (TransitionEvent) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val titles = listOf(stringResource(id = R.string.expense), stringResource(id = R.string.income))
    val (currentTab,
        currentData,
        amount,
        note,
        isEnabledButton,
        listCategory,
        currentCategory,
        currentCurrency) = uiState
    val textFieldLabel = if (currentTab == 0) {
        stringResource(id = R.string.expense)
    } else {
        stringResource(id = R.string.income)
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (tabs, dataRange, column, button) = createRefs()

        MonoTabs(
            modifier = Modifier
                .testTag(TestTags.TRANSACTION_TABS)
                .zIndex(100f)
                .constrainAs(tabs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            state = currentTab,
            titles = titles,
            onSelect = { onEvent(TransitionEvent.SelectTab(it)) }
        )

        MonoDateRange(
            modifier = Modifier
                .zIndex(100f)
                .padding(horizontal = 16.dp)
                .constrainAs(dataRange) {
                    top.linkTo(tabs.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            currentDate = currentData,
            onPrevious = { onEvent(TransitionEvent.PreviousDate) },
            onNext = { onEvent(TransitionEvent.NextDate) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                .constrainAs(column) {
                    top.linkTo(tabs.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

            Spacer(modifier = Modifier.height(72.dp))

            MonoTextField(
                modifier = Modifier.testTag(TestTags.TRANSACTION_AMOUNT),
                label = textFieldLabel,
                value = amount,
                textStyle = MaterialTheme.typography.displayLarge,
                trailingIcon = {
                    Text(
                        text = currentCurrency.symbol,
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                placeholder = {
                    Text(
                        text = "0.00",
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { onEvent(TransitionEvent.ChangeAmount(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MonoTextField(
                modifier = Modifier.testTag(TestTags.TRANSACTION_NOTE),
                label = stringResource(id = R.string.note),
                value = note,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.please_input),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                singleLine = false,
                maxLines = 5,
                onValueChange = { onEvent(TransitionEvent.ChangeNote(it)) },
            )

            Spacer(modifier = Modifier.height(16.dp))

            MonoCategorySurface(
                modifier = Modifier.testTag(TestTags.TRANSACTION_CATEGORY),
                listCategoryUi = listCategory,
                title = stringResource(id = R.string.category),
                selectedCategory = currentCategory,
                onClickItem = {
                    keyboardController?.hide()
                    onEvent(TransitionEvent.SelectCategory(it))
                }
            )

            Spacer(modifier = Modifier.height(84.dp))
        }

        MonoButton(
            modifier = Modifier
                .testTag(TestTags.TRANSACTION_BUTTON)
                .padding(horizontal = 16.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = {
                keyboardController?.hide()
                onEvent(TransitionEvent.Submit)
            },
            text = stringResource(id = R.string.submit),
            enabled = isEnabledButton
        )
    }
}

@Preview(showBackground = true, name = "Light TransactionScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTransactionScreen() {
    MonoApplicationTheme {
        TransactionScreen(
            uiState = TransitionUiState(
                listCategory = List(12) {
                    CategoryUi(
                        id = it.toLong(),
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    )
                },
                currentCurrency = Currency(
                    id = 1,
                    name = "Ruble",
                    letterCode = "RUB",
                    symbol = "₽",
                ),
                isEnabledButton = true
            )
        )
    }
}
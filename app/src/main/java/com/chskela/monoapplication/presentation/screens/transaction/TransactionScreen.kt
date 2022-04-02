package com.chskela.monoapplication.presentation.screens.transaction

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.datarange.MonoDateRange
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.screens.transaction.models.TransitionUiState
import com.chskela.monoapplication.presentation.ui.components.button.MonoButton
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@ExperimentalFoundationApi
@Composable
fun TransactionActivityScreen(
    transitionViewModel: TransitionViewModel = hiltViewModel(),
    bottomBar: @Composable () -> Unit = {}
) {
    TransactionScreen(
        uiState = transitionViewModel.uiState.value,
        onEvent = transitionViewModel::onEvent,
        bottomBar = bottomBar
    )
}

@ExperimentalFoundationApi
@Composable
fun TransactionScreen(
    uiState: TransitionUiState,
    onEvent: (TransitionEvent) -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
) {

    val scrollState = rememberScrollState()
    val titles = listOf(stringResource(id = R.string.expense), stringResource(id = R.string.income))
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        bottomBar = bottomBar,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        BoxWithConstraints {
            val heightTabs = 48.dp
            val heightBottomBar = 56.dp
            val heightScrollableColumn = maxHeight - heightTabs - heightBottomBar

            ConstraintLayout {
                val (tabs, rows, button) = createRefs()

                MonoTabs(
                    modifier = Modifier
                        .constrainAs(tabs) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    uiState.currentTab,
                    titles,
                    onSelect = { onEvent(TransitionEvent.SelectTab(it)) }
                )

                Column(modifier = Modifier
                    .height(height = heightScrollableColumn)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
                    .constrainAs(rows) {
                        top.linkTo(tabs.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    MonoDateRange(
                        currentDate = uiState.currentData,
                        onPrevious = { onEvent(TransitionEvent.PreviousData) },
                        onNext = { onEvent(TransitionEvent.NextData) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    MonoTextField(
                        label = if (uiState.currentTab == 0) {
                            stringResource(id = R.string.expense)
                        } else {
                            stringResource(id = R.string.income)
                        },
                        value = uiState.amount,
                        textStyle = MaterialTheme.typography.h1,
                        trailingIcon = {
                            Text(
                                text = uiState.currentCurrency.symbol,
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.onSurface
                            )
                        },
                        placeholder = {
                            Text(
                                text = "0.00",
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.secondaryVariant
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { onEvent(TransitionEvent.ChangeAmount(it)) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    MonoTextField(
                        label = stringResource(id = R.string.note),
                        value = uiState.note,
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.please_input),
                                style = MaterialTheme.typography.subtitle2,
                                color = MaterialTheme.colors.secondaryVariant
                            )
                        },
                        singleLine = false,
                        maxLines = 5,
                        onValueChange = { onEvent(TransitionEvent.ChangeNote(it)) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    MonoCategorySurface(
                        listCategoryUi = uiState.listCategory,
                        title = stringResource(id = R.string.category),
                        selectedCategory = uiState.currentCategory,
                        onClickItem = { onEvent(TransitionEvent.SelectCategory(it)) }
                    )

                    Spacer(modifier = Modifier.height(84.dp))
                }

                MonoButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .constrainAs(button) {
                            bottom.linkTo(parent.bottom, margin = 18.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    onClick = { onEvent(TransitionEvent.Submit) },
                    text = stringResource(id = R.string.submit),
                    enabled = uiState.enabledButton
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true, name = "Light TransactionScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTransactionScreen() {
    MonoApplicationTheme {
        TransactionScreen(
            uiState = TransitionUiState(
                listCategory = listOf(
                    CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 1,
                        icon ="bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 2,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 3,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 4,
                        icon ="bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 5,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 6,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 7,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 8,
                        icon ="bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 9,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 10,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    )
                ),
                currentCurrency = Currency(
                    id = 1,
                    name = "Ruble",
                    letterCode = "RUB",
                    symbol = "₽",
                )
            )
        )
    }
}
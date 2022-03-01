package com.chskela.monoapplication.presentation.screens.transaction

import android.content.res.Configuration
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.presentation.ui.components.monocategorysurface.CategoryUi
import com.chskela.monoapplication.presentation.screens.transaction.components.DateRange
import com.chskela.monoapplication.presentation.screens.transaction.components.TransitionTabs
import com.chskela.monoapplication.presentation.screens.transaction.models.TransitionUiState
import com.chskela.monoapplication.presentation.ui.components.bottomnavigation.MonoBottomNavigation
import com.chskela.monoapplication.presentation.ui.components.button.MonoButton
import com.chskela.monoapplication.presentation.ui.components.monocategorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun TransactionActivityScreen(
    transitionViewModel: TransitionViewModel = viewModel(),
) {
    TransactionScreen(
        uiState = transitionViewModel.uiState.value,
        onEvent = transitionViewModel::onEvent
    )
}

@Composable
fun TransactionScreen(
    uiState: TransitionUiState,
    onEvent: (TransitionEvent) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    val titles = listOf(stringResource(id = R.string.expense), stringResource(id = R.string.income))
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        bottomBar = { MonoBottomNavigation(selectedItem = 3, onClick = { TODO() }) },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        BoxWithConstraints {
            val heightTabs = 48.dp
            val heightBottomBar = 56.dp
            val heightScrollableColumn = maxHeight - heightTabs - heightBottomBar

            ConstraintLayout {
                val (tabs, rows, button) = createRefs()

                TransitionTabs(
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

                    DateRange(
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
                                text = "$",
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
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 1,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 2,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 3,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 4,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 5,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 6,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 7,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 8,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 9,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 10,
                        icon = R.drawable.category_bank,
                        title = stringResource(id = R.string.category_bank)
                    )
                ),
                currentCurrency = Currency(
                    id = 1,
                    name = "Ruble",
                    letterCode = "RUB",
                    symbol = "$",
                )
            )
        )
    }
}
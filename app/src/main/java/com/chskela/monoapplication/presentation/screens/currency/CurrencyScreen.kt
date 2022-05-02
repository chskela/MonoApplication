package com.chskela.monoapplication.presentation.screens.currency

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.presentation.screens.currency.models.CurrencyUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CurrencyActivityScreen(
    currencyViewModel: CurrencyViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    CurrencyScreen(
        uiState = currencyViewModel.uiState.value,
        onSelectedCurrency = currencyViewModel::selectDefaultCurrency,
        onBack = onBack
    )
}

@Composable
fun CurrencyScreen(
    uiState: CurrencyUiState,
    onSelectedCurrency: (Long) -> Unit = {},
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            MonoTopAppBar(title = stringResource(id = R.string.currency), onNavigation = onBack)
        },
        backgroundColor = MaterialTheme.colors.surface
    ) { padding ->
        LazyColumn(modifier = Modifier
            .padding(padding)
            .selectableGroup()) {
            items(uiState.currencyList) { currency ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (currency.id == uiState.selectedCurrency),
                            onClick = { onSelectedCurrency(currency.id) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (currency.id == uiState.selectedCurrency),
                        onClick = null,// null recommended for accessibility with screen readers
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                    )

                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(32.dp)
                            .border(
                                width = 1.dp, color = MaterialTheme.colors.secondaryVariant,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = currency.symbol,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${currency.symbol} 100.00",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        Text(
                            text = currency.letterCode,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                Divider(modifier = Modifier.padding(start = 56.dp, end = 16.dp))
            }
        }
    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCurrencyScreen() {
    MonoApplicationTheme {
        CurrencyScreen(
            uiState = CurrencyUiState(
                currencyList = listOf(
                    Currency(1, "Dollar", "USD", "$"),
                    Currency(2, "Dollar", "USD", "$"),
                    Currency(3, "Dollar", "USD", "$")
                ),
                selectedCurrency = 3
            )
        )
    }
}
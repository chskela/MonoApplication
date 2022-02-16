package com.chskela.monoapplication.presentation.screens.currency

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CurrencyActivityScreen(currencyViewModel: CurrencyViewModel = viewModel()) {
    CurrencyScreen(uiState = currencyViewModel.uiState.value)
}

@Composable
fun CurrencyScreen(uiState: CurrencyUiState) {
    Scaffold(topBar = {
        MonoTopAppBar(title = stringResource(id = R.string.currency))
    },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        val radioOptions = listOf("Calls", "Missed", "Friends")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        Column(Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null,// null recommended for accessibility with screenreaders
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                    )

                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(32.dp)
                            .clip(shape = RoundedCornerShape(4.dp))
                            .border(
                                width = 2.dp, color = MaterialTheme.colors.secondaryVariant
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "\$",
                            style = MaterialTheme.typography.body1,
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "\$100.00",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        Text(
                            text = "USD",
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
        CurrencyActivityScreen()
    }
}
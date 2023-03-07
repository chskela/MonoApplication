package com.chskela.monoapplication.presentation.screens.reports

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.reports.components.categoryreport.CategoryReportScreen
import com.chskela.monoapplication.presentation.screens.reports.components.dropchoice.DropChoice
import com.chskela.monoapplication.presentation.screens.reports.components.monthreport.MonthReportScreen
import com.chskela.monoapplication.presentation.screens.reports.models.Report
import com.chskela.monoapplication.presentation.screens.reports.models.ReportsUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    uiState: ReportsUiState,
    onEvent: (ReportsEvent) -> Unit = {},
    onSelectCategory: (Long) -> Unit = {}
) {
    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = uiState.title),
                navigationIcon = {
                    Spacer(modifier = Modifier.size(48.dp))
                },
                actions = {
                    IconButton(onClick = { onEvent(ReportsEvent.ToggleVisible) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.caret_down),
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = stringResource(R.string.caret_down)
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        ConstraintLayout(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
            val (content, modal) = createRefs()
            Crossfade(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                targetState = uiState.report
            ) { screen ->
                when (screen) {
                    Report.Category -> CategoryReportScreen(
                        expenseList = uiState.expenseList,
                        incomeList = uiState.incomeList,
                        onSelectCategory = onSelectCategory
                    )
                    Report.Month -> MonthReportScreen(
                        currentData = uiState.currentData,
                        currentBalance = uiState.currentBalance,
                        income = uiState.income,
                        expense = uiState.expense,
                        expenseIncome = uiState.expenseIncome,
                        previousBalance = uiState.previousBalance,
                        currentTab = uiState.currentTab,
                        transactionList = uiState.transactionList,
                        onEvent = onEvent,
                    )
                }
            }

            AnimatedVisibility(
                modifier = Modifier
                    .constrainAs(modal) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end, margin = 16.dp)
                    }, visible = uiState.isVisibleModal
            ) {
                DropChoice(
                    items = listOf(
                        stringResource(id = R.string.monthly),
                        stringResource(id = R.string.category)
                    ),
                    state = uiState.report,
                    onSelect = {
                        onEvent(ReportsEvent.SelectReport(it))
                        onEvent(ReportsEvent.ToggleVisible)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light ReportsScreen", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewReportsScreen() {
    MonoApplicationTheme {
        ReportsScreen(
            uiState = ReportsUiState()
        )
    }
}
package com.chskela.monoapplication.presentation.screens.reports

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.categoryreport.CategoryReportScreen
import com.chskela.monoapplication.presentation.screens.categoryreport.models.CategoryReportUiState
import com.chskela.monoapplication.presentation.screens.monthreport.MonthReportScreen
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import com.chskela.monoapplication.presentation.screens.reports.models.Report
import com.chskela.monoapplication.presentation.screens.reports.models.ReportsUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun ReportsScreen(
    uiState: ReportsUiState,
    onSelectCategory: (Long) -> Unit = {}
    ) {
        val scrollState = rememberScrollState()
        Scaffold(
            topBar = {
                MonoTopAppBar(
                    title = stringResource(id = R.string.category_report),
                    navigationIcon = {
                        Spacer(modifier = Modifier.size(48.dp))
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.caret_down),
                                tint = MaterialTheme.colors.secondary,
                                contentDescription = "caret down"
                            )
                        }
                    }
                )
            },
            backgroundColor = MaterialTheme.colors.surface
        ) { padding->
            Crossfade(modifier = Modifier.padding(padding), targetState = uiState.report) { content ->
                when(content) {
                    Report.Category -> CategoryReportScreen(uiState = CategoryReportUiState())
                    Report.Month -> MonthReportScreen(uiState = MonthReportUiState())
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
package com.chskela.monoapplication.presentation.screens.categoryreport.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.categoryreport.details.components.DetailsBigIcon
import com.chskela.monoapplication.presentation.screens.categoryreport.details.components.DetailsTabs
import com.chskela.monoapplication.presentation.screens.categoryreport.details.models.CategoryReportDetailsUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryReportDetailsActivityScreen(
    categoryReportDetailsViewModels: CategoryReportDetailsViewModels = hiltViewModel(),
    onBack: () -> Unit,
    categoryId: Long,
) {
    LaunchedEffect(true) {
//        categoryReportDetailsViewModels.onEvent(CategoryReportDetailsEvent.GetCategory(categoryId))
        categoryReportDetailsViewModels.onEvent(CategoryReportDetailsEvent.GetCategory(1))
    }

    CategoryReportDetailsScreen(
        uiState = categoryReportDetailsViewModels.uiState.value,
        onEvent = categoryReportDetailsViewModels::onEvent,
        onBack = onBack
    )
}

@Composable
fun CategoryReportDetailsScreen(
    uiState: CategoryReportDetailsUiState,
    onEvent: (CategoryReportDetailsEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.category_report),

                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.caret_down),
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "caret down"
                        )
                    }
                },
                onNavigation = onBack
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            DetailsTabs(
                state = uiState.currentTab,
                onSelect = { onEvent(CategoryReportDetailsEvent.SelectTab(it)) }
            )

            Spacer(modifier = Modifier.height(40.dp))

            DetailsBigIcon(
                title = uiState.categoryName,
                icon = uiState.icon,
                typeCategory = uiState.typeCategory
            )
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryReportScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryReportDetailsScreen() {
    MonoApplicationTheme {
        CategoryReportDetailsScreen(
            uiState = CategoryReportDetailsUiState()
        )


    }
}
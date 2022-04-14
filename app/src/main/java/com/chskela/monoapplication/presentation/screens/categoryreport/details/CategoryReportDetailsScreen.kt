package com.chskela.monoapplication.presentation.screens.categoryreport.details

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.categoryreport.models.CategoryReportUiState
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun CategoryReportDetailsActivityScreen(
    categoryReportDetailsViewModels: CategoryReportDetailsViewModels = hiltViewModel(),
    onBack: () -> Unit
) {
    CategoryReportDetailsScreen(
        uiState = categoryReportDetailsViewModels.uiState.value,
        onBack = onBack
    )
}

@Composable
fun CategoryReportDetailsScreen(
    uiState: CategoryReportUiState,
    onBack: () -> Unit = {}
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

            TabRow(
                modifier = Modifier
                    .size(height = 48.dp, width = 199.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = MaterialTheme.shapes.small
                    )
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(4.dp),
                selectedTabIndex = 1,
                indicator = @Composable { },
                divider = {},
                backgroundColor = MaterialTheme.colors.surface
            ) {
                listOf("Month", "Week").forEachIndexed { index, title ->
                    val selected = 1 == index
                    val color: Color by animateColorAsState(
                        targetValue = if (selected) {
                            MaterialTheme.colors.onSurface
                        } else {
                            MaterialTheme.colors.secondary
                        },
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
                    val background: Color by animateColorAsState(
                        targetValue = if (selected) {
                            MaterialTheme.colors.background
                        } else {
                            MaterialTheme.colors.surface
                        },
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
                    val shape = if (index == 0) {
                        RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
                    } else RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
                    Tab(
                        modifier = Modifier
                            .background(
                                color = background,
                                shape = shape
                            )
                            .clip(shape = shape),
                        text = {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.body1,
                                color = color
                            )
                        },
                        selected = selected,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryReportScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryReportDetailsScreen() {
    MonoApplicationTheme {
        CategoryReportDetailsScreen(
            uiState = CategoryReportUiState()
        )


    }
}
package com.chskela.monoapplication.presentation.screens.category

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    uiState: CategoryUiState,
    onEvent: (CategoryEvent) -> Unit = {},
    onBack: () -> Unit = {},
    onEdit: (Long) -> Unit = {},
    onAddMore: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    fun onDeleteCategory(categoryId: Long) {
        onEvent(CategoryEvent.DeleteCategory(categoryId))
        scope.launch {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = "Category deleted",
                actionLabel = "Undo"
            )
            if (result == SnackbarResult.ActionPerformed) {
                onEvent(CategoryEvent.Restore)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    shape = MaterialTheme.shapes.large,
                    backgroundColor = MaterialTheme.colors.background,
                    action = {
                        data.actionLabel?.let { it ->
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        data.performAction()
                                    }
                                    .padding(8.dp),
                                text = it.uppercase(),
                                style = MaterialTheme.typography.body1
                                    .copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                ) {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        },
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = R.string.category), onNavigation = onBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddMore) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add_more))
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MonoCategorySurface(
                listCategoryUi = uiState.expenseList,
                title = stringResource(id = R.string.expense),
                onClickItem = onEdit,
                onLongClick = ::onDeleteCategory
            )
            MonoCategorySurface(
                listCategoryUi = uiState.incomeList,
                title = stringResource(id = R.string.income),
                onClickItem = onEdit,
                onLongClick = ::onDeleteCategory
            )
        }
    }
}

@Preview(showBackground = true, name = "Light CategoryScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryScreen() {
    MonoApplicationTheme {
        CategoryScreen(
            uiState = CategoryUiState(
                expenseList = listOf(
                    CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(id = 0, title = "Add more")
                ),
                incomeList = listOf(
                    CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(
                        id = 0,
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    ), CategoryUi(id = 0, title = "Add more")
                ),
            )
        )
    }
}
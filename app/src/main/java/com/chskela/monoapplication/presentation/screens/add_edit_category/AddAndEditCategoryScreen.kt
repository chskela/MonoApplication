package com.chskela.monoapplication.presentation.screens.add_edit_category

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.screens.add_edit_category.models.AddAndEditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.MonoCategorySurface
import com.chskela.monoapplication.presentation.ui.components.tabs.MonoTabs
import com.chskela.monoapplication.presentation.ui.components.textfield.MonoTextField
import com.chskela.monoapplication.presentation.ui.components.topappbar.MonoTopAppBar
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAndEditCategoryScreen(
    uiState: AddAndEditCategoryUiState,
    onEvent: (AddAndEditCategoryEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val titles = listOf(stringResource(id = R.string.expense), stringResource(id = R.string.income))
    val (_, currentTab, categoryName, currentIcon, _, icons, isNewCategory) = uiState
    val titleId = if (isNewCategory) R.string.add_category else R.string.edit_category

    val (imageVector, contentDescription) = if (isNewCategory) {
        Icons.Default.Add to R.string.add
    } else Icons.Default.Edit to R.string.save

    val addAndEditCategoryEvent = if (isNewCategory) {
        AddAndEditCategoryEvent.AddAndCategory
    } else {
        AddAndEditCategoryEvent.UpdateCategoryAnd
    }

    Scaffold(
        topBar = {
            MonoTopAppBar(
                title = stringResource(id = titleId),
                actions = {
                    IconButton(onClick = {
                        onEvent(addAndEditCategoryEvent)
                        onBack()
                    }) {
                        Icon(
                            imageVector = imageVector,
                            contentDescription = stringResource(id = contentDescription)
                        )
                    }
                },
                onNavigation = onBack
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        ConstraintLayout(modifier = Modifier.padding(padding)) {
            val (column, tabs, textField) = createRefs()

            MonoTabs(modifier = Modifier
                .zIndex(100f)
                .constrainAs(tabs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                state = currentTab,
                titles = titles,
                onSelect = { onEvent(AddAndEditCategoryEvent.SelectTab(it)) }
            )

            MonoTextField(
                modifier = Modifier
                    .zIndex(100f)
                    .padding(horizontal = 16.dp)
                    .constrainAs(textField) {
                        top.linkTo(tabs.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                label = stringResource(id = R.string.category_name),
                value = categoryName,
                onValueChange = { onEvent(AddAndEditCategoryEvent.ChangeCategoryNameAnd(it)) }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(start = 16.dp, top = 104.dp, end = 16.dp)
                    .constrainAs(column) {
                        top.linkTo(tabs.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.height(40.dp))
                MonoCategorySurface(
                    listCategoryUi = icons,
                    title = stringResource(id = R.string.icon),
                    selectedCategory = icons
                        .firstOrNull { it.icon == currentIcon }?.id ?: -1,
                    onClickItem = { onEvent(AddAndEditCategoryEvent.ChangeCategoryIconAnd(it)) }
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview(showBackground = true, name = "Light EditCategoryScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewEditCategoryScreen() {
    MonoApplicationTheme {
        AddAndEditCategoryScreen(
            uiState = AddAndEditCategoryUiState(
                icons = List(20) {
                    CategoryUi(
                        id = it.toLong(),
                        icon = "bank",
                        title = stringResource(id = R.string.category_bank)
                    )
                }
            ),
        )
    }
}
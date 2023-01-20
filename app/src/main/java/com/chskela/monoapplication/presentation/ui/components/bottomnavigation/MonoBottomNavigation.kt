package com.chskela.monoapplication.presentation.ui.components.bottomnavigation

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chskela.monoapplication.presentation.navigation.BottomMenuScreens
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme3

@Composable
fun MonoBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = NavigationBarDefaults.Elevation,
    items: List<BottomMenuScreens> = emptyList(),
    navController: NavHostController,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val label = stringResource(screen.label)
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = contentColor,
                ),
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            if (isSelected) screen.iconActive else screen.icon
                        ),
                        contentDescription = label,
                    )
                },
                label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
                selected = isSelected,
                onClick = {
                    try {
                        navController.popBackStack()
                        navController.navigate(screen.route)
                    } catch (e: IllegalStateException) {
                        if (e.message != "Already attached to lifecycleOwner") {
                            throw e
                        } else {
                            // You can log the exception if you want
                        }
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, name = "Light MonoBottomNavigation")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoBottomNavigation() {
    MonoApplicationTheme3 {
        Surface {
            MonoBottomNavigation(navController = rememberNavController())
        }
    }
}
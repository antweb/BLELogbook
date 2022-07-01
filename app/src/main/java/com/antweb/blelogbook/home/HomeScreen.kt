package com.antweb.blelogbook.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.antweb.blelogbook.home.overview.OverviewScreen
import com.antweb.blelogbook.home.overview.OverviewViewModel
import com.antweb.blelogbook.home.scan.ScanScreen

@Composable
fun HomeScreen(model: HomeViewModel) {
    val navController = rememberNavController()

    val items = listOf(
        Screen.Overview,
        Screen.Scan,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val onTitleChange = { title: String -> model.setTitle(title) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(model.title.collectAsState().value) }
            )
        },
        bottomBar = {
            BottomNavigation {


                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.labelId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        content = {
            NavHost(navController = navController, startDestination = Screen.Overview.route) {
                composable(Screen.Overview.route) {
                    OverviewScreen(hiltViewModel(), onTitleChange)
                }

                composable(Screen.Scan.route) { ScanScreen(onTitleChange) }
            }
        }
    )
}
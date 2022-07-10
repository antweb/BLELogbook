package com.antweb.blelogbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antweb.blelogbook.feature.home.HomeScreen
import com.antweb.blelogbook.feature.home.HomeViewModel
import com.antweb.blelogbook.feature.start.StartScreen
import com.antweb.blelogbook.feature.start.StartScreenViewModel
import com.antweb.blelogbook.ui.theme.BLELogbookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BLELogbookTheme {
                Navigator()
            }
        }
    }
}

@Composable
fun Navigator() {
    val navController = rememberNavController()

    val navHandler = { route: String, options: NavOptions? ->
        navController.navigate(route, options)
    }

    return NavHost(navController = navController, startDestination = Routes.start) {
        composable(Routes.start) {
            val viewModel = viewModel<StartScreenViewModel>()
            StartScreen(viewModel, navHandler)
        }

        composable(Routes.home) {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(viewModel)
        }
    }
}

typealias NavHandler = (route: String, options: NavOptions?) -> Unit
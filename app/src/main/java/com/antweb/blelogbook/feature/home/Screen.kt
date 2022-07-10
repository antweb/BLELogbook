package com.antweb.blelogbook.feature.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BluetoothSearching
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.antweb.blelogbook.R

sealed class Screen(
    val route: String,
    @StringRes val labelId: Int,
    val icon: ImageVector,
) {

    object Overview : Screen(
        "/home/overview",
        R.string.home_bottom_nav_overview,
        Icons.Filled.Home,
    )

    object Scan : Screen(
        "/home/scan",
        R.string.home_bottom_nav_scan,
        Icons.Filled.BluetoothSearching,
    )
}
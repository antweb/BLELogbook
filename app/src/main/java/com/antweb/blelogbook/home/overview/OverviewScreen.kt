package com.antweb.blelogbook.home.overview

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.antweb.blelogbook.R

@Composable
fun OverviewScreen(setTitle: (String) -> Unit) {
    setTitle(stringResource(R.string.home_overview_title))

    Text("Overview")
}
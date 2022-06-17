package com.antweb.blelogbook.home.scan

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.antweb.blelogbook.R

@Composable
fun ScanScreen(setTitle: (String) -> Unit) {
    setTitle(stringResource(R.string.home_scan_title))

    Text("Scan")
}
package com.antweb.blelogbook.home.scan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.antweb.blelogbook.R
import com.antweb.blelogbook.bluetooth.model.ScannedDevice

@Composable
fun ScanScreen(model: ScanViewModel, setTitle: (String) -> Unit) {
    setTitle(stringResource(R.string.home_scan_title))

    val isScanning = model.isScanning.collectAsState().value

    Column {
        ScanButton(isScanning) {
            if (!isScanning) {
                model.startScan()
            } else {
                model.stopScan()
            }
        }

        ResultList(model.scanResults)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultList(devices: List<ScannedDevice>) {
    LazyColumn {
        items(
            items = devices,
            key = { device -> device.address }
        ) { device ->
            val name = device.name ?: stringResource(R.string.home_scan_unknown)
            val address = device.address

            ListItem(
                text = { Text("$address - $name") }
            )
            Divider()
        }
    }
}

@Composable
fun ScanButton(isScanning: Boolean, onClick: () -> Unit) {
    Button(onClick) {
        if (!isScanning) {
            Text(stringResource(R.string.home_scan_start_scan))
        } else {
            Text(stringResource(R.string.home_scan_stop_scan))
        }
    }
}
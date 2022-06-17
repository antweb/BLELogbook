package com.antweb.blelogbook.start

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.antweb.blelogbook.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState


@Composable
fun StartScreen(model: StartScreenViewModel) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PermissionCheck()
            }
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PermissionCheck() {
    val bluetoothPermissionState = rememberPermissionState(
        android.Manifest.permission.BLUETOOTH_CONNECT
    )

    val context = LocalContext.current

    if (bluetoothPermissionState.hasPermission) {
        Text(context.getString(R.string.start_loading))
    } else {
        Column(
            modifier = Modifier.padding(top = 176.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = context.getString(R.string.start_first_things_first),
                style = MaterialTheme.typography.h5,
            )

            Text(
                text = context.getString(R.string.start_permission_required),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp),
            )

            Button(
                modifier = Modifier.padding(top = 24.dp),
                enabled = !bluetoothPermissionState.shouldShowRationale,
                onClick = {
                    bluetoothPermissionState.launchPermissionRequest()
                },
            ) {
                Text(
                    text = context.getString(R.string.start_grant_permission),
                )
            }

            TextButton(
                onClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", context.packageName, null)
                    startActivity(context, intent, null)
                },
            ) {
                Text(
                    text = context.getString(R.string.start_open_app_settings),
                )
            }
        }
    }
}
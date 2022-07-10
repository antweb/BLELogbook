package com.antweb.blelogbook.feature.home.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.antweb.blelogbook.R

@Composable
fun OverviewScreen(model: OverviewViewModel, setTitle: (String) -> Unit) {
    setTitle(stringResource(R.string.home_overview_title))

    Column(
        modifier = Modifier.padding(all = 24.dp)
    ) {
        AdapterStatusCard(
            model.adapterEnabled.collectAsState(),
            model.adapterDiscovering.collectAsState(),
            model.adapterName.collectAsState(),
        )
    }
}

@Composable
private fun AdapterStatusCard(
    isEnabled: State<Boolean>,
    isDiscovering: State<Boolean>,
    adapterName: State<String>,
) {
    Card(
        elevation = 1.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                stringResource(R.string.home_overview_adapter_status),
                style = MaterialTheme.typography.h6,
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            if (isEnabled.value) {
                Text(stringResource(R.string.home_overview_adapter_enabled))
            } else {
                Text(stringResource(R.string.home_overview_adapter_disabled))
            }

            Text(
                stringResource(R.string.home_overview_adapter_enabled_status),
                style = MaterialTheme.typography.caption,
            )

            if (isEnabled.value) {
                FullAdapterInfo(isDiscovering = isDiscovering)
            }

            Text(
                adapterName.value,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                stringResource(R.string.home_overview_adapter_name),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}

@Composable
fun FullAdapterInfo(
    isDiscovering: State<Boolean>,
) {
    val discoveringText =
        if (isDiscovering.value) stringResource(R.string.home_overview_yes)
        else stringResource(R.string.home_overview_no)

    Text(
        discoveringText,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(top = 16.dp)
    )
    Text(
        stringResource(R.string.home_overview_discovering),
        style = MaterialTheme.typography.caption,
    )
}
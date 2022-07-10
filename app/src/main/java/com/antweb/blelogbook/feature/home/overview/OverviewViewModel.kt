package com.antweb.blelogbook.feature.home.overview

import androidx.lifecycle.ViewModel
import com.antweb.blelogbook.core.bluetooth.AdapterService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    adapterService: AdapterService
) : ViewModel() {

    private val _adapterEnabled = MutableStateFlow(adapterService.isEnabled())
    private val _adapterDiscovering = MutableStateFlow(adapterService.isDiscovering())
    private val _adapterName = MutableStateFlow(adapterService.getAdapterName())

    val adapterEnabled: StateFlow<Boolean> = _adapterEnabled
    val adapterDiscovering = _adapterDiscovering
    val adapterName: StateFlow<String> = _adapterName
}
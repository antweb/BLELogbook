package com.antweb.blelogbook.home.scan

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.antweb.blelogbook.bluetooth.ScanService
import com.antweb.blelogbook.bluetooth.model.ScannedDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val scanService: ScanService,
) : ViewModel() {
    private val _isScanning = MutableStateFlow(false)
    private val scanResultMap = mutableMapOf<String, ScannedDevice>()
    private val addressesWithName = mutableSetOf<String>()

    val scanResults = mutableStateListOf<ScannedDevice>() //TODO: make read-only
    val isScanning: StateFlow<Boolean> = _isScanning

    fun startScan() {
        scanService.startScan(
            onScanResult = { device: ScannedDevice ->
                val address = device.address

                if (!scanResultMap.contains(address)) {
                    scanResultMap[address] = device
                    scanResults.clear()
                    scanResults.addAll(scanResultMap.values)

                    if (!device.name.isNullOrEmpty()) {
                        addressesWithName.add(address)
                    }
                } else {
                    if (!addressesWithName.contains(address) && !device.name.isNullOrEmpty()) {
                        addressesWithName.add(address)

                        scanResultMap[address] = device
                        scanResults.clear()
                        scanResults.addAll(scanResultMap.values)
                    }
                }
            },
            onError = {}
        )
        _isScanning.value = true
    }

    fun stopScan() {
        scanService.stopScan()
        _isScanning.value = false
    }
}
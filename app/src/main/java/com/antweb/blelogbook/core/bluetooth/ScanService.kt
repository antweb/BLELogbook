package com.antweb.blelogbook.core.bluetooth

import com.antweb.blelogbook.core.bluetooth.model.ScannedDevice

interface ScanService {

    fun startScan(onScanResult: (ScannedDevice) -> Unit, onError: (errorCode: Int) -> Unit)

    fun stopScan()
}
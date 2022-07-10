package com.antweb.blelogbook.bluetooth

import com.antweb.blelogbook.bluetooth.model.ScannedDevice

interface ScanService {

    fun startScan(onScanResult: (ScannedDevice) -> Unit, onError: (errorCode: Int) -> Unit)

    fun stopScan()
}
package com.antweb.blelogbook.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import com.antweb.blelogbook.bluetooth.model.ScannedDevice
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ScanServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ScanService {
    private val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private var currentCallback: ScanCallback? = null

    @SuppressLint("MissingPermission")
    override fun startScan(
        onScanResult: (ScannedDevice) -> Unit,
        onError: (errorCode: Int) -> Unit
    ) {
        val scanner = manager.adapter.bluetoothLeScanner

        if (scanner == null) {
            onError(-1)
            return
        }

        if (currentCallback == null) {
            currentCallback = buildCallback(
                { scanResult ->
                    if (scanResult.device != null) {
                        onScanResult(ScannedDevice(scanResult.device))
                    }
                },
                onError
            )
        }

        scanner.startScan(currentCallback)
    }

    @SuppressLint("MissingPermission")
    override fun stopScan() {
        if (currentCallback == null) {
            // Scan already stopped or never started
            return
        }

        manager.adapter.bluetoothLeScanner?.stopScan(currentCallback)
        currentCallback = null
    }

    private fun buildCallback(
        onScanResult: (ScanResult) -> Unit,
        onError: (errorCode: Int) -> Unit
    ): ScanCallback = object : ScanCallback() {

        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            if (result == null) {
                return
            }

            onScanResult(result)
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            if (results == null) {
                return
            }

            results.forEach(onScanResult)
        }

        override fun onScanFailed(errorCode: Int) {
            onError(errorCode)
        }
    }
}
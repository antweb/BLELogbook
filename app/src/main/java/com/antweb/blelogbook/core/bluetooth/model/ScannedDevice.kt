package com.antweb.blelogbook.core.bluetooth.model

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice

data class ScannedDevice(
    val name: String?,
    val address: String,
) {
    @SuppressLint("MissingPermission")
    constructor(device: BluetoothDevice) : this(device.name, device.address)
}
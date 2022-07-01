package com.antweb.blelogbook.bluetooth

import android.bluetooth.BluetoothManager
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class AdapterService @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    fun isEnabled() = manager.adapter.isEnabled

    fun isDiscovering() = manager.adapter.isDiscovering

    fun getAdapterName() = manager.adapter.name
    
}
package com.antweb.blelogbook.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class AdapterServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AdapterService {
    private val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    override fun isEnabled() = manager.adapter.isEnabled

    @SuppressLint("MissingPermission")
    override fun isDiscovering() = manager.adapter.isDiscovering

    @SuppressLint("MissingPermission")
    override fun getAdapterName() = manager.adapter.name ?: ""
}
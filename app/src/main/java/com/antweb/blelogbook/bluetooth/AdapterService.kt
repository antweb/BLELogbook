package com.antweb.blelogbook.bluetooth

interface AdapterService {
    fun isEnabled(): Boolean

    fun isDiscovering(): Boolean

    fun getAdapterName(): String
}
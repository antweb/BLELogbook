package com.antweb.blelogbook.core.bluetooth

interface AdapterService {
    fun isEnabled(): Boolean

    fun isDiscovering(): Boolean

    fun getAdapterName(): String
}
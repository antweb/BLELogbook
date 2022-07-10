package com.antweb.blelogbook.core.bluetooth

class ConnectedAdapterServiceFake : AdapterService {
    val name = "Test Device"

    override fun isEnabled() = true

    override fun isDiscovering() = false

    override fun getAdapterName() = name
}
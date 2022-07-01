package com.antweb.blelogbook.bluetooth

class ConnectedAdapterServiceFake : AdapterService {
    val name = "Test Device"

    override fun isEnabled() = true

    override fun isDiscovering() = false

    override fun getAdapterName() = name
}
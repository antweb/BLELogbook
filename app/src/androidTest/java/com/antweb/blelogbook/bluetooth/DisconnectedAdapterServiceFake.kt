package com.antweb.blelogbook.bluetooth

class DisconnectedAdapterServiceFake : AdapterService {
    val name = "Test Device"

    override fun isEnabled() = false

    override fun isDiscovering() = false

    override fun getAdapterName() = name
}
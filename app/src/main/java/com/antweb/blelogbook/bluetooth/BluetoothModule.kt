package com.antweb.blelogbook.bluetooth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BluetoothModule {

    @Singleton
    @Binds
    abstract fun bindAdapterService(service: AdapterServiceImpl): AdapterService
}
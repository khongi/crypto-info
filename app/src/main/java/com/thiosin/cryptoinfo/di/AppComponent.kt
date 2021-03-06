package com.thiosin.cryptoinfo.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import com.thiosin.cryptoinfo.data.disk.DiskModule
import com.thiosin.cryptoinfo.data.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        RainbowCakeModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DiskModule::class
    ]
)
interface AppComponent : RainbowCakeComponent
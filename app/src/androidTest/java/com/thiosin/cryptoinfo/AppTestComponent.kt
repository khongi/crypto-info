package com.thiosin.cryptoinfo

import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import com.thiosin.cryptoinfo.data.disk.DiskModule
import com.thiosin.cryptoinfo.data.network.NetworkModule
import com.thiosin.cryptoinfo.di.AppComponent
import com.thiosin.cryptoinfo.di.ApplicationModule
import com.thiosin.cryptoinfo.di.ViewModelModule
import com.thiosin.cryptoinfo.ui.details.DetailsTest
import com.thiosin.cryptoinfo.ui.list.ListTest
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
interface AppTestComponent : AppComponent {

    fun inject(listTest: ListTest)

    fun inject(detailsTest: DetailsTest)

}
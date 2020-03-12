package com.thiosin.cryptoinfo.data.network

import com.thiosin.cryptoinfo.data.network.coinlayer.CoinLayerApi
import com.thiosin.cryptoinfo.data.network.coinlib.CoinLibApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCoinLayerApi(): CoinLayerApi = TODO()

    @Provides
    @Singleton
    fun provideCoinLibApi(): CoinLibApi = TODO()

}
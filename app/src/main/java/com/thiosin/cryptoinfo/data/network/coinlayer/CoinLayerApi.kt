package com.thiosin.cryptoinfo.data.network.coinlayer

import com.thiosin.cryptoinfo.data.network.coinlayer.models.CoinLayerListApiResponse
import com.thiosin.cryptoinfo.data.network.common.models.Currencies
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinLayerApi {

    @GET("list")
    suspend fun getList(
        @Query("pref") pref: String = Currencies.USD.name
    ): CoinLayerListApiResponse

}
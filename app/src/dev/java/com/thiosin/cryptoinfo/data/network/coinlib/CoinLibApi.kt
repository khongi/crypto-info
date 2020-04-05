package com.thiosin.cryptoinfo.data.network.coinlib

import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibCoinApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListOrder.RANK_ASC
import com.thiosin.cryptoinfo.data.network.common.models.Currencies
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinLibApi {

    @GET("coinlist")
    suspend fun getCoinList(
        @Query("pref") pref: String = Currencies.USD.name,
        @Query("order") order: String = RANK_ASC.value,
        @Query("page") page: Int = 1
    ): CoinLibListApiResponse

    @GET("coin")
    suspend fun getCoin(
        @Query("symbol") symbol: String,
        @Query("pref") pref: String = Currencies.USD.name
    ): CoinLibCoinApiResponse

}
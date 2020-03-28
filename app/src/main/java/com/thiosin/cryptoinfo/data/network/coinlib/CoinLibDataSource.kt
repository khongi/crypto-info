package com.thiosin.cryptoinfo.data.network.coinlib

import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibCoinApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListApiResponse
import com.thiosin.cryptoinfo.util.network.NetworkResponse
import com.thiosin.cryptoinfo.util.network.executeNetworkCall
import javax.inject.Inject

class CoinLibDataSource @Inject constructor(private val coinLibApi: CoinLibApi) {

    suspend fun getCoin(symbol: String): NetworkResponse<CoinLibCoinApiResponse> =
        executeNetworkCall {
            coinLibApi.getCoin(symbol)
        }

    suspend fun getCoinList(): NetworkResponse<CoinLibListApiResponse> =
        executeNetworkCall {
            coinLibApi.getCoinList()
        }

}
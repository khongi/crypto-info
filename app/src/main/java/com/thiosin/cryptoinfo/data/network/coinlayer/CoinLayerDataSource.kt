package com.thiosin.cryptoinfo.data.network.coinlayer

import com.thiosin.cryptoinfo.data.network.coinlayer.models.CoinLayerListApiResponse
import util.network.NetworkResponse
import util.network.executeNetworkCall
import javax.inject.Inject

class CoinLayerDataSource @Inject constructor(private val coinLayerApi: CoinLayerApi) {

    suspend fun getCoinList(): NetworkResponse<CoinLayerListApiResponse> = executeNetworkCall {
        coinLayerApi.getList()
    }

}
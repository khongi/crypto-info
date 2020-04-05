package com.thiosin.cryptoinfo.data.network

import com.thiosin.cryptoinfo.data.network.coinlayer.CoinLayerApi
import com.thiosin.cryptoinfo.data.network.coinlib.CoinLibApi
import com.thiosin.cryptoinfo.domain.models.GetCoinDto
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto
import com.thiosin.cryptoinfo.util.network.NetworkResponse
import com.thiosin.cryptoinfo.util.network.executeNetworkCall
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val coinLibApi: CoinLibApi,
    private val coinLayerApi: CoinLayerApi
) {

    suspend fun getCoin(symbol: String): NetworkResponse<GetCoinDto> =
        executeNetworkCall {
            coinLibApi.getCoin(symbol).let {
                GetCoinDto(
                    symbol = it.symbol,
                    price = it.price.toDouble(),
                    low24h = it.low24h.toDouble(),
                    high24h = it.high24h.toDouble(),
                    delta1h = it.delta1h.toDouble(),
                    delta24h = it.delta24h.toDouble(),
                    delta7d = it.delta7d.toDouble()
                )
            }
        }

    suspend fun getCoinList(): NetworkResponse<List<GetCoinsDto>> =
        executeNetworkCall {
            val coinLayerResponse = coinLayerApi.getList()
            val coinLibResponse = coinLibApi.getCoinList()

            val coins: MutableList<GetCoinsDto> = mutableListOf()
            coinLibResponse.coins.forEach {
                val coinLayerCoin = coinLayerResponse.crypto[it.symbol]
                coins.add(
                    GetCoinsDto(
                        symbol = it.symbol,
                        name = it.name,
                        price = it.price.toDouble(),
                        rank = it.rank,
                        delta24h = it.delta24h.toDouble(),
                        iconUrl = coinLayerCoin?.iconUrl ?: ""
                    )
                )
            }
            coins
        }

}
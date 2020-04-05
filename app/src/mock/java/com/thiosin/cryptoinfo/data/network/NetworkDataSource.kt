package com.thiosin.cryptoinfo.data.network


import com.thiosin.cryptoinfo.domain.models.GetCoinDto
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto
import com.thiosin.cryptoinfo.util.network.NetworkResponse
import com.thiosin.cryptoinfo.util.network.NetworkResult
import javax.inject.Inject

class NetworkDataSource @Inject constructor() {

    suspend fun getCoin(symbol: String): NetworkResponse<GetCoinDto> {
        return NetworkResult(
            GetCoinDto(
                symbol = "BTC",
                price = 6088.72,
                delta1h = -1.1,
                delta24h = -2.2,
                delta7d = 3.3,
                low24h = 6075.03,
                high24h = 6267.01
            )
        )
    }

    suspend fun getCoinList(): NetworkResponse<List<GetCoinsDto>> {
        return NetworkResult(
            listOf(
                GetCoinsDto(
                    symbol = "BTC",
                    name = "Bitcoin",
                    price = 6088.72,
                    rank = 1,
                    delta24h = -2.2,
                    iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
                )
            )
        )
    }


}
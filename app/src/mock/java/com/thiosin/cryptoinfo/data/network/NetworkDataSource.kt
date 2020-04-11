package com.thiosin.cryptoinfo.data.network


import com.thiosin.cryptoinfo.data.common.DOMAIN_GETCOINDTO
import com.thiosin.cryptoinfo.data.common.DOMAIN_GETCOINSDTO_LIST
import com.thiosin.cryptoinfo.domain.models.GetCoinDto
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto
import com.thiosin.cryptoinfo.util.network.NetworkResponse
import com.thiosin.cryptoinfo.util.network.NetworkResult
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER", "RedundantSuspendModifier")
class NetworkDataSource @Inject constructor() {

    suspend fun getCoin(symbol: String): NetworkResponse<GetCoinDto> {
        return NetworkResult(DOMAIN_GETCOINDTO)
    }

    suspend fun getCoinList(): NetworkResponse<List<GetCoinsDto>> {
        return NetworkResult(DOMAIN_GETCOINSDTO_LIST)
    }

}
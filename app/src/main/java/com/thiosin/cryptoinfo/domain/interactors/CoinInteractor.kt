package com.thiosin.cryptoinfo.domain.interactors

import com.thiosin.cryptoinfo.data.disk.DiskDataSource
import com.thiosin.cryptoinfo.data.network.NetworkDataSource
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto
import com.thiosin.cryptoinfo.domain.toDomainCoin
import com.thiosin.cryptoinfo.util.network.*
import javax.inject.Inject

class CoinInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val diskDataSource: DiskDataSource
) {

    suspend fun getCoins(refresh: Boolean = false): DataTransferResponse<List<DomainCoin>> {
        if (!refresh) {
            return DataTransferSuccess(diskDataSource.getAllCoins())
        }
        return when (val response = networkDataSource.getCoinList()) {
            NetworkUnavailable, NetworkIOError -> NetworkUnavailableCached(diskDataSource.getAllCoins())
            is NetworkHttpError -> NetworkErrorCached(diskDataSource.getAllCoins())
            is NetworkResult -> {
                val newCoinsDtos = response.result
                val newDomainCoins = newCoinsDtos.map(GetCoinsDto::toDomainCoin)
                diskDataSource.updateCoins(newDomainCoins)
                DataTransferSuccess(diskDataSource.getAllCoins())
            }
        }
    }

    suspend fun getCoin(
        symbol: String,
        refresh: Boolean = false
    ): DataTransferResponse<DomainCoin> {
        val cachedCoin = diskDataSource.getCoinBySymbol(symbol)
        if (!refresh) {
            return DataTransferSuccess(cachedCoin)
        }
        return when (val response = networkDataSource.getCoin(symbol)) {
            NetworkUnavailable, NetworkIOError -> NetworkUnavailableCached(cachedCoin)
            is NetworkHttpError -> NetworkErrorCached(cachedCoin)
            is NetworkResult -> {
                val newCoinDto = response.result
                val newDomainCoin = cachedCoin.copy(
                    price = newCoinDto.price,
                    low24h = newCoinDto.low24h,
                    high24h = newCoinDto.high24h,
                    delta1h = newCoinDto.delta1h,
                    delta24h = newCoinDto.delta24h,
                    delta7d = newCoinDto.delta7d
                )
                DataTransferSuccess(newDomainCoin)
            }
        }
    }

}
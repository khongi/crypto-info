package com.thiosin.cryptoinfo.domain.interactors

import com.thiosin.cryptoinfo.data.disk.DiskDataSource
import com.thiosin.cryptoinfo.data.network.NetworkDataSource
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto
import com.thiosin.cryptoinfo.domain.toDomainCoin
import com.thiosin.cryptoinfo.util.network.NetworkResult
import timber.log.Timber
import javax.inject.Inject

class CoinInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val diskDataSource: DiskDataSource
) {

    fun getCachedCoins(): List<DomainCoin>? {
        return try {
            Timber.d("Retrieving coins from database")
            diskDataSource.getAllCoins()
        } catch (t: Throwable) {
            Timber.e(t)
            null
        }
    }

    fun getCachedCoinsBySymbol(symbol: String): List<DomainCoin> {
        return try {
            Timber.d("Looking for $symbol in database")
            diskDataSource.getAllCoinsBySymbol(symbol)
        } catch (t: Throwable) {
            Timber.e(t)
            emptyList()
        }
    }

    suspend fun getNetworkCoins(): List<DomainCoin>? {
        Timber.d("Retrieving coins from network")
        return when (val response = networkDataSource.getCoinList()) {
            is NetworkResult -> {
                val newCoinsDtos = response.result
                val newDomainCoins = newCoinsDtos.map(GetCoinsDto::toDomainCoin)
                Timber.d("Updating database with new coins")
                diskDataSource.updateCoins(newDomainCoins)
                getCachedCoins()
            }
            else -> null
        }
    }

    fun getCachedCoin(symbol: String): DomainCoin {
        return try {
            Timber.d("Retrieving single coin from database")
            diskDataSource.getCoinBySymbol(symbol)
        } catch (t: Throwable) {
            Timber.e(t)
            throw IllegalStateException("Coin $symbol does not exist in database")
        }
    }

    suspend fun getNetworkCoin(symbol: String): DomainCoin? {
        val cachedCoin = getCachedCoin(symbol)
        return when (val response = networkDataSource.getCoin(symbol)) {
            is NetworkResult -> {
                val newCoinDto = response.result
                val newCoin = cachedCoin.copy(
                    price = newCoinDto.price,
                    low24h = newCoinDto.low24h,
                    high24h = newCoinDto.high24h,
                    delta1h = newCoinDto.delta1h,
                    delta24h = newCoinDto.delta24h,
                    delta7d = newCoinDto.delta7d
                )
                diskDataSource.updateCoin(newCoin)
                getCachedCoin(symbol)
            }
            else -> null
        }
    }

}
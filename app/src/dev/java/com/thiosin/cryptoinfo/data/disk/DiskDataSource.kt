package com.thiosin.cryptoinfo.data.disk

import com.thiosin.cryptoinfo.data.disk.dao.CoinDao
import com.thiosin.cryptoinfo.data.disk.models.RoomCoin
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class DiskDataSource @Inject constructor(
    private val coinDao: CoinDao
) {

    fun getAllCoins(): List<DomainCoin> {
        return coinDao.getAllCoins().map(RoomCoin::toDomainCoin)
    }

    fun getAllCoinsBySymbol(symbol: String): List<DomainCoin> {
        return coinDao.getAllCoinsBySymbol(symbol).map(RoomCoin::toDomainCoin)
    }

    fun updateCoins(coins: List<DomainCoin>) {
        coinDao.refreshCoins(coins.map(DomainCoin::toRoomCoin))
    }

    fun updateCoin(coin: DomainCoin) {
        coinDao.updateCoin(coin.toRoomCoin())
    }

    fun getCoinBySymbol(symbol: String): DomainCoin {
        return coinDao.getCoinBySymbol(symbol).toDomainCoin()
    }

}
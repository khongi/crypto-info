package com.thiosin.cryptoinfo.data.disk.dao

import androidx.room.*
import com.thiosin.cryptoinfo.data.disk.models.RoomCoin

@Dao
abstract class CoinDao {

    @Query("SELECT * FROM coins")
    abstract fun getAllCoins(): List<RoomCoin>

    @Query("SELECT * FROM coins WHERE symbol = :symbol")
    abstract fun getAllCoinsBySymbol(symbol: String): List<RoomCoin>

    @Query("SELECT * FROM coins WHERE symbol = :symbol")
    abstract fun getCoinBySymbol(symbol: String): RoomCoin

    @Insert
    abstract fun insertCoins(coins: List<RoomCoin>)

    @Update
    abstract fun updateCoin(coin: RoomCoin)

    @Query("DELETE FROM coins")
    abstract fun removeAllCoins()

    @Transaction
    open fun refreshCoins(coins: List<RoomCoin>) {
        removeAllCoins()
        insertCoins(coins)
    }

}
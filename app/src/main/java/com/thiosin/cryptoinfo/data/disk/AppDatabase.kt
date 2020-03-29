package com.thiosin.cryptoinfo.data.disk

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thiosin.cryptoinfo.data.disk.dao.CoinDao
import com.thiosin.cryptoinfo.data.disk.models.RoomCoin

@Database(entities = [RoomCoin::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

}
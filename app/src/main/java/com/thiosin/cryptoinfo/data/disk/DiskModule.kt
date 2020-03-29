package com.thiosin.cryptoinfo.data.disk

import android.content.Context
import androidx.room.Room
import com.thiosin.cryptoinfo.data.disk.dao.CoinDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DiskModule {

    companion object {
        private const val DB_NAME = "coin-info-db"
    }

    @Provides
    @Singleton
    fun provideCoinDao(db: AppDatabase): CoinDao = db.coinDao()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }

}
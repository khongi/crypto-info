package com.thiosin.cryptoinfo.data.disk

import com.thiosin.cryptoinfo.data.disk.dao.CoinDao
import javax.inject.Inject

class DiskDataSource @Inject constructor(
    private val coinDao: CoinDao
) {

}
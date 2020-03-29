package com.thiosin.cryptoinfo.data.disk.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Coins")
data class RoomCoin(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var symbol: String,
    var name: String,
    var price: Double,
    var rank: Int,
    var delta24h: Double,
    var iconUrl: String,
    val low24h: Double?,
    val high24h: Double?,
    val delta1h: Double?,
    val delta7d: Double?
)
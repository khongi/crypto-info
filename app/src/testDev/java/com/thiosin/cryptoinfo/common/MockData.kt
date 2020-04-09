package com.thiosin.cryptoinfo.common

import com.thiosin.cryptoinfo.data.disk.models.RoomCoin
import com.thiosin.cryptoinfo.data.network.coinlayer.models.CoinLayerCoin
import com.thiosin.cryptoinfo.data.network.coinlayer.models.CoinLayerListApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibCoinApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListCoin
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.domain.models.GetCoinDto
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto

object BTC {
    const val symbol = "BTC"
    const val name = "Bitcoin"
    const val price = 7312.0701358619
    const val rank = 1
    const val delta1h = -0.19
    const val delta24h = -0.53
    const val delta7d = 15.68
    const val low24h = 7114.7503569226
    const val high24h = 7428.0425412873
    const val iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
}

object ETH {
    const val symbol = "ETH"
    const val name = "Ethereum"
    const val price = 170.0376093918
    const val rank = 2
    const val delta1h = -0.56
    const val delta24h = -1.58
    const val delta7d = 27.52
    const val low24h = 163.3306438671
    const val high24h = 174.8423532766
    const val iconUrl = "https://assets.coinlayer.com/icons/ETH.png"
}

val NETWORK_COINLIB_COIN_RESPONSE =
    CoinLibCoinApiResponse(
        symbol = BTC.symbol,
        price = "${BTC.price}",
        delta1h = "${BTC.delta1h}",
        delta24h = "${BTC.delta24h}",
        delta7d = "${BTC.delta7d}",
        low24h = "${BTC.low24h}",
        high24h = "${BTC.high24h}"
    )

val NETWORK_COINLIB_LIST_RESPONSE =
    CoinLibListApiResponse(
        coins = arrayOf(
            CoinLibListCoin(
                symbol = BTC.symbol,
                name = BTC.name,
                price = "${BTC.price}",
                rank = BTC.rank,
                delta24h = "${BTC.delta24h}"
            ),
            CoinLibListCoin(
                symbol = ETH.symbol,
                name = ETH.name,
                price = "${ETH.price}",
                rank = ETH.rank,
                delta24h = "${ETH.delta24h}"
            )
        )
    )

val NETWORK_COINLAYER_LIST_RESPONSE =
    CoinLayerListApiResponse(
        crypto = mapOf(
            Pair(
                BTC.symbol, CoinLayerCoin(
                    symbol = BTC.symbol,
                    name = BTC.name,
                    iconUrl = BTC.iconUrl
                )
            ),
            Pair(
                ETH.symbol, CoinLayerCoin(
                    symbol = ETH.symbol,
                    name = ETH.name,
                    iconUrl = ETH.iconUrl
                )
            )
        )
    )

val ROOM_COINS = listOf(
    RoomCoin(
        symbol = BTC.symbol,
        name = BTC.name,
        price = BTC.price,
        rank = BTC.rank,
        delta1h = BTC.delta1h,
        delta24h = BTC.delta24h,
        delta7d = BTC.delta7d,
        low24h = BTC.low24h,
        high24h = BTC.high24h,
        iconUrl = BTC.iconUrl
    ),
    RoomCoin(
        symbol = ETH.symbol,
        name = ETH.name,
        price = ETH.price,
        rank = ETH.rank,
        delta1h = ETH.delta1h,
        delta24h = ETH.delta24h,
        delta7d = ETH.delta7d,
        low24h = ETH.low24h,
        high24h = ETH.high24h,
        iconUrl = ETH.iconUrl
    )
)

val DOMAIN_COINS = listOf(
    DomainCoin(
        symbol = BTC.symbol,
        name = BTC.name,
        price = BTC.price,
        rank = BTC.rank,
        delta1h = BTC.delta1h,
        delta24h = BTC.delta24h,
        delta7d = BTC.delta7d,
        low24h = BTC.low24h,
        high24h = BTC.high24h,
        iconUrl = BTC.iconUrl
    ),
    DomainCoin(
        symbol = ETH.symbol,
        name = ETH.name,
        price = ETH.price,
        rank = ETH.rank,
        delta1h = ETH.delta1h,
        delta24h = ETH.delta24h,
        delta7d = ETH.delta7d,
        low24h = ETH.low24h,
        high24h = ETH.high24h,
        iconUrl = ETH.iconUrl
    )
)

val DOMAIN_GETCOINDTO =
    GetCoinDto(
        symbol = BTC.symbol,
        price = BTC.price,
        delta1h = BTC.delta1h,
        delta24h = BTC.delta24h,
        delta7d = BTC.delta7d,
        low24h = BTC.low24h,
        high24h = BTC.high24h
    )

val DOMAIN_GETCOINSDTO_LIST =
    listOf(
        GetCoinsDto(
            symbol = BTC.symbol,
            name = BTC.name,
            price = BTC.price,
            rank = BTC.rank,
            delta24h = BTC.delta24h,
            iconUrl = BTC.iconUrl
        ),
        GetCoinsDto(
            symbol = ETH.symbol,
            name = ETH.name,
            price = ETH.price,
            rank = ETH.rank,
            delta24h = ETH.delta24h,
            iconUrl = ETH.iconUrl
        )
    )

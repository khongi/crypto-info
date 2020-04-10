package com.thiosin.cryptoinfo.common

import com.thiosin.cryptoinfo.data.disk.models.RoomCoin
import com.thiosin.cryptoinfo.data.network.coinlayer.models.CoinLayerCoin
import com.thiosin.cryptoinfo.data.network.coinlayer.models.CoinLayerListApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibCoinApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListApiResponse
import com.thiosin.cryptoinfo.data.network.coinlib.models.CoinLibListCoin

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

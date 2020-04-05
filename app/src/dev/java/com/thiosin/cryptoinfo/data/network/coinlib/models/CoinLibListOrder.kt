package com.thiosin.cryptoinfo.data.network.coinlib.models

enum class CoinLibListOrder(val value: String) {
    RANK_ASC("rank_asc"),
    RANK_DESC("rank_desc"),
    VOLUME_ASC("volume_asc"),
    VOLUME_DESC("volume_desc"),
    PRICE_ASC("price_asc"),
    PRICE_DESC("price_desc"),
    DATE_INSERTED_ASC("date_inserted_asc"),
    DATE_INSERTED_DESC("date_inserted_desc")
}
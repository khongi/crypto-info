/**
 * CoinInfo
 * Coin Info application API
 *
 * OpenAPI spec version: 1.0.0
 * Contact: b.khongi@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package com.thiosin.cryptoinfo.data.network.coinlib.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * @param coins
 */
@JsonClass(generateAdapter = true)
data class CoinLibListApiResponse(
    @Json(name = "coins") val coins: Array<CoinLibListCoin>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CoinLibListApiResponse

        if (!coins.contentEquals(other.coins)) return false

        return true
    }

    override fun hashCode(): Int {
        return coins.contentHashCode()
    }

}
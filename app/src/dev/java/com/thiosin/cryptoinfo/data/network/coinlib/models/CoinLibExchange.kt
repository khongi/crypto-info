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
 * @param name
 * @param volume24h
 * @param price
 */
@JsonClass(generateAdapter = true)
data class CoinLibExchange(
    @Json(name = "name") val name: String,
    @Json(name = "volume_24h") val volume24h: String,
    @Json(name = "price") val price: String
)
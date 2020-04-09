package com.thiosin.cryptoinfo.data.network

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.common.*
import com.thiosin.cryptoinfo.data.network.coinlayer.CoinLayerApi
import com.thiosin.cryptoinfo.data.network.coinlib.CoinLibApi
import com.thiosin.cryptoinfo.util.network.NetworkResult
import com.thiosin.cryptoinfo.util.network.isInternetAvailable
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NetworkDataSourceTest {

    private val coinLibApi: CoinLibApi = mockk()
    private val coinLayerApi: CoinLayerApi = mockk()

    private val networkDataSource = NetworkDataSource(coinLibApi, coinLayerApi)

    @Before
    fun setUp() {
        clearMocks(coinLibApi, coinLayerApi)
        mockkStatic("com.thiosin.cryptoinfo.util.network.NetworkUtilsKt")
        every { isInternetAvailable() } returns true
    }

    @Test
    fun `getCoin maps network response correctly`() = runBlocking {
        coEvery { coinLibApi.getCoin(BTC.symbol) } returns NETWORK_COINLIB_COIN_RESPONSE

        val response = networkDataSource.getCoin(BTC.symbol)
        val coin = (response as NetworkResult).result

        assertThat(coin).isEqualTo(DOMAIN_GETCOINDTO)
    }

    @Test
    fun `getCoin calls api method with symbol param`() = runBlocking {
        coEvery { coinLibApi.getCoin(BTC.symbol) } returns NETWORK_COINLIB_COIN_RESPONSE

        networkDataSource.getCoin(BTC.symbol)

        coVerify { coinLibApi.getCoin(BTC.symbol) }
    }

    @Test
    fun `getCoinList merges api call results`() = runBlocking {
        coEvery { coinLibApi.getCoinList() } returns NETWORK_COINLIB_LIST_RESPONSE
        coEvery { coinLayerApi.getList() } returns NETWORK_COINLAYER_LIST_RESPONSE

        val response = networkDataSource.getCoinList()
        val coins = (response as NetworkResult).result

        assertThat(coins).containsExactlyElementsIn(DOMAIN_GETCOINSDTO_LIST).inOrder()
    }

    @Test
    fun `getCoinList calls api methods`() = runBlocking {
        coEvery { coinLibApi.getCoinList() } returns NETWORK_COINLIB_LIST_RESPONSE
        coEvery { coinLayerApi.getList() } returns NETWORK_COINLAYER_LIST_RESPONSE

        networkDataSource.getCoinList()

        coVerifyAll {
            coinLibApi.getCoinList()
            coinLayerApi.getList()
        }
    }
}
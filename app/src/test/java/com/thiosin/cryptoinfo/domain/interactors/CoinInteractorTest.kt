package com.thiosin.cryptoinfo.domain.interactors

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.data.disk.DiskDataSource
import com.thiosin.cryptoinfo.data.network.NetworkDataSource
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto
import com.thiosin.cryptoinfo.util.network.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoinInteractorTest {

    companion object {
        private val MOCK_DATABASE_COINS = listOf(
            DomainCoin(
                symbol = "BTC",
                name = "Bitcoin",
                price = 6088.72,
                rank = 1,
                delta1h = -1.1,
                delta24h = -2.2,
                delta7d = 3.3,
                low24h = 6075.03,
                high24h = 6267.01,
                iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
            )
        )
        private val MOCK_NETWORKS_COINS = listOf(
            GetCoinsDto(
                symbol = "BTC",
                name = "Bitcoin",
                price = 6088.72,
                rank = 1,
                delta24h = -2.2,
                iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
            )
        )
    }

    private val networkDataSource: NetworkDataSource = mockk(relaxed = true)
    private val diskDataSource: DiskDataSource = mockk(relaxed = true)

    private val coinInteractor = CoinInteractor(
        networkDataSource = networkDataSource,
        diskDataSource = diskDataSource
    )

    @Test
    fun `getNetworkCoins returns objects from database`() = runBlocking {
        every { diskDataSource.getAllCoins() } returns MOCK_DATABASE_COINS
        coEvery { networkDataSource.getCoinList() } returns NetworkResult(MOCK_NETWORKS_COINS)

        val response = coinInteractor.getNetworkCoins()

        assertThat(response).contains(MOCK_DATABASE_COINS[0])
    }

}
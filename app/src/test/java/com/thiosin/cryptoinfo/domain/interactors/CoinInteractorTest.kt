package com.thiosin.cryptoinfo.domain.interactors

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.data.disk.DiskDataSource
import com.thiosin.cryptoinfo.data.network.NetworkDataSource
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.util.network.DataTransferSuccess
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoinInteractorTest {

    companion object {
        private val MOCK_COINS = listOf(
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
    }

    private val networkDataSource: NetworkDataSource = mockk(relaxed = true)
    private val diskDataSource: DiskDataSource = mockk(relaxed = true)

    private val coinInteractor = CoinInteractor(
        networkDataSource = networkDataSource,
        diskDataSource = diskDataSource
    )

    @Test
    fun `getCoins returns objects from database`() = runBlocking {
        every { diskDataSource.getAllCoins() } returns MOCK_COINS

        val response = coinInteractor.getCoins()

        assertThat(response).isInstanceOf(DataTransferSuccess::class.java)
        assertThat((response as DataTransferSuccess).result).contains(MOCK_COINS[0])
    }

}
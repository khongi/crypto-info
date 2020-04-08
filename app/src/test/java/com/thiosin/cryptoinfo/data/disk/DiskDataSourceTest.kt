package com.thiosin.cryptoinfo.data.disk

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.common.DOMAIN_COINS
import com.thiosin.cryptoinfo.common.ROOM_COINS
import com.thiosin.cryptoinfo.data.disk.dao.CoinDao
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DiskDataSourceTest {

    private val coinDao: CoinDao = mockk()
    private val diskDataSource = DiskDataSource(coinDao)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getAllCoins mapping works`() {
        every { coinDao.getAllCoins() } returns ROOM_COINS

        val coins = diskDataSource.getAllCoins()

        verify { coinDao.getAllCoins() }
        assertThat(coins).containsExactlyElementsIn(DOMAIN_COINS)
    }

    @Test
    fun `getAllCoinsBySymbol adds sql like wildcards to query string`() {
        val expectedQuery = "%${ROOM_COINS[0].symbol}%"
        every { coinDao.getAllCoinsBySymbol(expectedQuery) } returns listOf(ROOM_COINS[0])

        diskDataSource.getAllCoinsBySymbol(ROOM_COINS[0].symbol)

        verify { coinDao.getAllCoinsBySymbol(expectedQuery) }
    }

    @Test
    fun `getAllCoinsBySymbol returns correct coins`() {
        every { coinDao.getAllCoinsBySymbol("%MOCK%") } returns listOf(ROOM_COINS[0])

        val coins = diskDataSource.getAllCoinsBySymbol("MOCK")

        assertThat(coins).containsExactlyElementsIn(listOf(DOMAIN_COINS[0]))
    }

    @Test
    fun `updateCoins sends correct list to dao`() {
        every { coinDao.refreshCoins(ROOM_COINS) } returns Unit

        diskDataSource.updateCoins(DOMAIN_COINS)

        verify { coinDao.refreshCoins(ROOM_COINS) }
    }

    @Test
    fun `updateCoin sends correct coin to dao`() {
        every { coinDao.updateCoin(ROOM_COINS[0]) } returns Unit

        diskDataSource.updateCoin(DOMAIN_COINS[0])

        verify { coinDao.updateCoin(ROOM_COINS[0]) }
    }

    @Test
    fun `getCoinBySymbol mapping works`() {
        every { coinDao.getCoinBySymbol(DOMAIN_COINS[0].symbol) } returns ROOM_COINS[0]

        val coin = diskDataSource.getCoinBySymbol(DOMAIN_COINS[0].symbol)

        assertThat(coin).isEqualTo(DOMAIN_COINS[0])
    }
}
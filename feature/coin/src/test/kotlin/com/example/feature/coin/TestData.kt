package com.example.feature.coin

import com.example.feature.coin.domain.Coin
import com.example.feature.currency.Currency
import com.example.network.CoinResponse
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

val pageSize: Int = 50

val bitcoinCoinResponse: CoinResponse = CoinResponse(
    id = "btc",
    name = "Bitcoin",
    currentPrice = "95000",
    marketCap = "950000",
    marketCapRank = "1"
)

val ethereumCoinResponse: CoinResponse = CoinResponse(
    id = "eth",
    name = "Ethereum",
    currentPrice = "3400",
    marketCap = "34000",
    marketCapRank = "2"
)

val solanaCoinResponse: CoinResponse = CoinResponse(
    id = "sol",
    name = "Solana",
    currentPrice = "192.44",
    marketCap = "19200",
    marketCapRank = "6"
)

val bitcoinCoin: Coin = Coin(
    id = "btc",
    name = "Bitcoin",
    price = "95000",
    marketCap = "950000",
    rank = "1",
    currency = Currency.USD
)

val ethereumCoin: Coin = Coin(
    id = "eth",
    name = "Ethereum",
    price = "3400",
    marketCap = "34000",
    rank = "2",
    currency = Currency.USD
)

val solanaCoin: Coin = Coin(
    id = "sol",
    name = "Solana",
    price = "192.44",
    marketCap = "19200",
    rank = "6",
    currency = Currency.USD
)

val coinResponseList: List<CoinResponse> = buildList {
    repeat(pageSize / 2) {
        add(bitcoinCoinResponse)
        add(ethereumCoinResponse)
    }
}

val coinResponseList2Page: List<CoinResponse> = buildList {
    repeat(pageSize / 2) {
        add(solanaCoinResponse)
        add(bitcoinCoinResponse)
    }
}

val coinList: ImmutableList<Coin> = buildList {
    repeat(pageSize / 2) {
        add(bitcoinCoin)
        add(ethereumCoin)
    }
}.toImmutableList()

val coinList2Pages: ImmutableList<Coin> = buildList {
    repeat(pageSize / 2) {
        add(bitcoinCoin)
        add(ethereumCoin)
    }
    repeat(pageSize / 2) {
        add(solanaCoin)
        add(bitcoinCoin)
    }
}.toImmutableList()
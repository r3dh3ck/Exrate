package com.example.feature.coin

import com.example.network.CoinResponse

val bitcoinCoinResponse: CoinResponse = CoinResponse(
    id = "btc",
    symbol = "btc",
    name = "Bitcoin",
    image = "btc_url",
    currentPrice = "95000",
    marketCap = 950000L,
    marketCapRank = "1"
)

val ethereumCoinResponse: CoinResponse = CoinResponse(
    id = "eth",
    symbol = "eth",
    name = "Ethereum",
    image = "eth_url",
    currentPrice = "3400",
    marketCap = 34000L,
    marketCapRank = "2"
)

val solanaCoinResponse: CoinResponse = CoinResponse(
    id = "sol",
    symbol = "sol",
    name = "Solana",
    image = "sol_url",
    currentPrice = "192.44",
    marketCap = 19200L,
    marketCapRank = "6"
)

val coinResponseList: List<CoinResponse> = buildList {
    repeat(25) {
        add(bitcoinCoinResponse)
        add(ethereumCoinResponse)
    }
}

val coinResponseList2Page: List<CoinResponse> = buildList {
    repeat(25) {
        add(solanaCoinResponse)
        add(bitcoinCoinResponse)
    }
}
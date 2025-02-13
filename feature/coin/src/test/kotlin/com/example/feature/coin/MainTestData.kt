package com.example.feature.coin

import com.example.feature.coin.ui.main.coinlist.item.CoinItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

val bitcoinCoinItem: CoinItem = CoinItem(
    id = "btc",
    imageUrl = "btc_url",
    symbol = "BTC",
    name = "Bitcoin",
    price = "95,000 USD",
)

val ethereumCoinItem: CoinItem = CoinItem(
    id = "eth",
    imageUrl = "eth_url",
    symbol = "ETH",
    name = "Ethereum",
    price = "3,400 USD"
)

val solanaCoinItem: CoinItem = CoinItem(
    id = "sol",
    imageUrl = "sol_url",
    symbol = "SOL",
    name = "Solana",
    price = "192.44 USD"
)

val coinItemList: ImmutableList<CoinItem> = buildList {
    repeat(25) {
        add(bitcoinCoinItem)
        add(ethereumCoinItem)
    }
}.toImmutableList()

val coinItemList2Pages: ImmutableList<CoinItem> = buildList {
    repeat(25) {
        add(bitcoinCoinItem)
        add(ethereumCoinItem)
    }
    repeat(25) {
        add(solanaCoinItem)
        add(bitcoinCoinItem)
    }
}.toImmutableList()
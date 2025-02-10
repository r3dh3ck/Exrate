package com.example.feature.coin.ui.main.coinlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data object CoinListLoadingItem {
    val key: String = toString()
}

@Composable
internal fun CoinListLoadingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 88.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinListLoadingItemPreview() {
    CoinListLoadingItem()
}
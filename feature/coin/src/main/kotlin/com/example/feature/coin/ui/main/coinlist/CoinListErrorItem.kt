package com.example.feature.coin.ui.main.coinlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.coin.R
import com.example.core.R as coreR

data object CoinListErrorItem {
    val key: String = toString()
}

@Composable
internal fun CoinListErrorItem(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 88.dp)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(coreR.string.internal_error),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(R.string.coin_list_retry_button_text)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinListErrorItemPreview() {
    CoinListErrorItem(
        onRetry = {}
    )
}
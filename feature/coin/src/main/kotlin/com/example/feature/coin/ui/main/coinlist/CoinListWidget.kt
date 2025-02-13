package com.example.feature.coin.ui.main.coinlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.lazyListItemPosition
import com.example.feature.coin.R
import com.example.feature.coin.ui.main.coinlist.item.CoinItem
import com.example.feature.coin.ui.main.coinlist.item.CoinListErrorItem
import com.example.feature.coin.ui.main.coinlist.item.CoinListItem
import com.example.feature.coin.ui.main.coinlist.item.CoinListLoadingItem
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.filter
import com.example.core.R as coreR

const val COIN_LIST_WIDGET_TEST_TAG = "coin_list_widget_test_tag"
private const val BUFFER_SIZE = 10

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CoinListWidget(
    state: CoinListState,
    contentPadding: PaddingValues,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val isRefreshing = state is CoinListState.Content &&
            state.refresh is CoinListState.LoadState.Loading
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { onAction(CoinListAction.Refresh) }
    )
    Box(
        modifier = modifier.fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        CoinListWidget(
            state = state,
            contentPadding = contentPadding,
            onAction = onAction
        )
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun CoinListWidget(
    state: CoinListState,
    contentPadding: PaddingValues,
    onAction: (CoinListAction) -> Unit
) {
    when (state) {
        is CoinListState.Loading -> Loading()
        is CoinListState.Content -> Content(
            state = state,
            contentPadding = contentPadding,
            onLoadMore = remember { { onAction(CoinListAction.LoadMore) } },
            onRetry = remember { { onAction(CoinListAction.RetryLoadMore) } },
            onCoinClicked = remember { { coin -> onAction(CoinListAction.CoinClicked(coin)) } }
        )
        is CoinListState.Error -> Error(
            onRetry = remember { { onAction(CoinListAction.Retry) } }
        )
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Content(
    state: CoinListState.Content,
    contentPadding: PaddingValues,
    onLoadMore: () -> Unit,
    onRetry: () -> Unit,
    onCoinClicked: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        contentPadding = contentPadding,
        modifier = Modifier.testTag(COIN_LIST_WIDGET_TEST_TAG)
    ) {
        itemsIndexed(
            items = state.items,
            key = { _, coinItem -> coinItem.id },
            contentType = { _, _ -> CoinListItem }
        ) { index, coinItem ->
            CoinListItem(
                coinItem = coinItem,
                modifier = Modifier.padding(top = 2.dp)
                    .clickable { onCoinClicked(coinItem.id) }
                    .lazyListItemPosition(index)
            )
        }
        when (state.loadMore) {
            is CoinListState.LoadState.Loading -> {
                item(
                    key = CoinListLoadingItem.key,
                    contentType = CoinListLoadingItem,
                    content = { CoinListLoadingItem() }
                )
            }
            CoinListState.LoadState.Error -> {
                item(
                    key = CoinListErrorItem.key,
                    contentType = CoinListErrorItem,
                    content = { CoinListErrorItem(onRetry) }
                )
            }
            CoinListState.LoadState.NotLoaded -> Unit
        }
    }
    LoadMoreEffect(
        state = state,
        lazyListState = lazyListState,
        onLoadMore = onLoadMore
    )
}

@Composable
private fun Error(
    onRetry: () -> Unit
) {
    Column(
        verticalArrangement = spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(coreR.string.internal_error)
        )
        Button(onRetry) {
            Text(
                text = stringResource(R.string.coin_list_retry_button_text)
            )
        }
    }
}

@Composable
private fun LoadMoreEffect(
    state: CoinListState.Content,
    lazyListState: LazyListState,
    onLoadMore: () -> Unit
) {
    val loadMore = remember(state) {
        derivedStateOf {
            val totalItemsCount = lazyListState.layoutInfo.totalItemsCount
            val visibleItemsInfo = lazyListState.layoutInfo.visibleItemsInfo
            val lastVisibleItemIndex = visibleItemsInfo.lastOrNull()?.index ?: 0
            val isIdle = state.refresh is CoinListState.LoadState.NotLoaded &&
                    state.loadMore is CoinListState.LoadState.NotLoaded
            lastVisibleItemIndex >= (totalItemsCount - BUFFER_SIZE) && isIdle
        }
    }
    LaunchedEffect(state) {
        snapshotFlow { loadMore.value }
            .filter { loadMore -> loadMore }
            .collect { onLoadMore() }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    CoinListWidget(
        state = CoinListState.Loading,
        contentPadding = PaddingValues(),
        onAction = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    val items = persistentListOf(
        CoinItem.newInstance(
            id = "btc",
            symbol = "btc",
            name = "Bitcoin",
            price = "100,000 USD"
        ),
        CoinItem.newInstance(
            id = "eth",
            symbol = "eth",
            name = "Ethereum",
            price = "3,500.53 USD"
        ),
        CoinItem.newInstance(
            id = "usdt",
            symbol = "usdt",
            name = "Tether",
            price = "0.99 USD"
        )
    )
    val state = CoinListState.Content(
        items = items,
        loadMore = CoinListState.LoadState.NotLoaded,
        refresh = CoinListState.LoadState.NotLoaded
    )
    CoinListWidget(
        state = state,
        contentPadding = PaddingValues(),
        onAction = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    CoinListWidget(
        state = CoinListState.Error,
        contentPadding = PaddingValues(),
        onAction = {}
    )
}
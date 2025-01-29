package com.example.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val LazyListItemPosition = SemanticsPropertyKey<Int>("LazyListItemPosition")

private var SemanticsPropertyReceiver.lazyListItemPosition by LazyListItemPosition

fun Modifier.lazyListItemPosition(position: Int): Modifier {
    return semantics { lazyListItemPosition = position }
}
package com.example.feature.selectcurrency.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature.selectcurrency.R

const val QUERY_WIDGET_TEST_TAG = "query_widget_test_tag"
const val QUERY_WIDGET_CLEAR_BUTTON_TEST_TAG = "query_widget_clear_button_test_tag"

@Composable
internal fun QueryWidget(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        singleLine = true,
        placeholder = { Text(stringResource(R.string.select_currency_query_widget_placeholder)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = remember { { onQueryChanged("") } },
                    modifier = Modifier.testTag(QUERY_WIDGET_CLEAR_BUTTON_TEST_TAG)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier.fillMaxWidth()
            .testTag(QUERY_WIDGET_TEST_TAG)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun QueryWidgetPreview() {
    QueryWidget(
        query = "usd",
        onQueryChanged = {}
    )
}
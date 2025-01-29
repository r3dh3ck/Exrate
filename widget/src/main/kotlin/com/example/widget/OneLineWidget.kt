package com.example.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val ONE_LINE_WIDGET_VALUE_TEST_TAG = "one_line_widget_value_test_tag"

@Composable
fun OneLineWidget(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .background(color = MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = value,
            modifier = Modifier.align(Alignment.CenterEnd)
                .testTag(ONE_LINE_WIDGET_VALUE_TEST_TAG)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OneLineWidgetPreview() {
    OneLineWidget(
        label = "label",
        value = "value"
    )
}
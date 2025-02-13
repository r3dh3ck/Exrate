package com.example.feature.coin.ui

import java.math.BigDecimal
import java.text.DecimalFormat

internal class AmountFormatter {

    private val formatter = DecimalFormat().apply {
        groupingSize = 3
        minimumFractionDigits = 0
        maximumFractionDigits = 8
    }

    fun format(value: Long): String {
        return formatter.format(value)
    }

    fun format(value: BigDecimal): String {
        return formatter.format(value)
    }
}
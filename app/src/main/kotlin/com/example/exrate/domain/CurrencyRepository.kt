package com.example.exrate.domain

import kotlinx.collections.immutable.ImmutableList

interface CurrencyRepository {
    suspend fun getCurrencyList(): Result<ImmutableList<Currency>>
    suspend fun getCurrency(id: String): Result<Currency>
}
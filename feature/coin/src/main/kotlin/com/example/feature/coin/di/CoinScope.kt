package com.example.feature.coin.di

import javax.inject.Scope

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Scope
internal annotation class CoinScope
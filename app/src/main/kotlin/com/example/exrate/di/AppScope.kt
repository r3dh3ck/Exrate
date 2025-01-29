package com.example.exrate.di

import javax.inject.Scope

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Scope
annotation class AppScope
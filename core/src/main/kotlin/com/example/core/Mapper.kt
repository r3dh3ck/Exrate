package com.example.core

interface Mapper<T, R> {
    fun map(value: T): R
}
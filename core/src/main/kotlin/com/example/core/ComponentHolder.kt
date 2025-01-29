package com.example.core

abstract class ComponentHolder<T> {

    private var creator: (() -> T)? = null
    private var componentRef: T? = null

    fun get(): T {
        var component = componentRef
        if (component != null) {
            return component
        }
        component = creator?.invoke()
        componentRef = component
        return component ?: throw IllegalStateException("Component is not initialized")
    }

    fun set(create: () -> T) {
        creator = create
    }

    fun clear() {
        componentRef = null
    }
}
package com.kuxln.kuxlndi

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : Any> di(): ReadOnlyProperty<Any?, T> {
    return object : ReadOnlyProperty<Any?, T> {
        private var cached: T? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            if (cached == null) {
                cached = DIContainer.getObjectInstance(T::class)
            }

            return cached!!
        }
    }
}

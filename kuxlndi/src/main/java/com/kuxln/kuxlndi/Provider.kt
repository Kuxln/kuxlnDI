package com.kuxln.kuxlndi

import kotlin.reflect.KClass

interface Provider<T : Any> {
    val kClass: KClass<T>
    val implKClass: KClass<out T>
    fun get(): T
}

internal fun Provider<*>.getIsSingleton(): Boolean {
    val annotationList = this.implKClass.annotations
    if (annotationList.isEmpty()) {
        return false
    }

    return annotationList.any { annotation -> annotation.annotationClass == Singleton::class }
}

inline fun <reified T : Any, reified Impl : T> provider(
    noinline creator: () -> Impl
): Provider<T> =
    object : Provider<T> {
        override val kClass: KClass<T> = T::class
        override val implKClass = Impl::class
        override fun get(): T = creator()
    }
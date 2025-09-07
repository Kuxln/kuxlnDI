package com.kuxln.kuxlndi

interface ModuleScope {
    fun add(provider: Provider<*>)

    fun add(diModule: DIModule)
}

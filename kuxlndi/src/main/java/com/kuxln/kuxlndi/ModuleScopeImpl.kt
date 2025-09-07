package com.kuxln.kuxlndi

import android.util.Log

class ModuleScopeImpl : ModuleScope {
    private val _registeredDependencies = mutableListOf<Provider<*>>()
    val registeredDependencies: List<Provider<*>>
        get() = _registeredDependencies

    private val _registeredModules = mutableListOf<DIModule>()
    val registeredModules: List<DIModule>
        get() = _registeredModules

    override fun add(provider: Provider<*>) {
        val isSingletonDependency = provider.getIsSingleton()
        if (isSingletonDependency) {
            Log.w(
                "DIContainer",
                "Provided dependency ${provider::class.qualifiedName} marked with a Singleton annotation, while registered inside default module." +
                        "The dependency won't be used as Singleton"
            )
        }

        _registeredDependencies.add(provider)
    }

    override fun add(diModule: DIModule) {
        if (diModule is SingletonDIModule) {
            Log.w(
                "DIContainer",
                "Provided module ${diModule::class.qualifiedName} is inheritor of SingletonDIModule, while registered like default module" +
                        "The module will be treated as default module"
            )
        }

        _registeredModules.add(diModule)
    }
}

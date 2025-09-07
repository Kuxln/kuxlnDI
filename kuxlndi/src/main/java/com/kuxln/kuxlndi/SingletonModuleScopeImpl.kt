package com.kuxln.kuxlndi

internal class SingletonModuleScopeImpl : SingletonModuleScope {
    private val _registeredDependencies = mutableListOf<Provider<*>>()
    val registeredDependencies: List<Provider<*>>
        get() = _registeredDependencies

    private val _registeredModules = mutableListOf<DIModule>()
    val registeredModules: List<DIModule>
        get() = _registeredModules

    override fun add(provider: Provider<*>) {
        val isSingletonDependency = provider.getIsSingleton()
        if (!isSingletonDependency) {
            throw IllegalArgumentException("Dependency ${provider.implKClass.qualifiedName} doesn't marked with a Singleton annotation, while trying to add to SingletonDIModule")
        }

        _registeredDependencies.add(provider)
    }

    override fun add(diModule: DIModule) {
        if (diModule !is SingletonDIModule) {
            throw IllegalArgumentException("DIModule ${diModule::class.qualifiedName} doesn't inheritor of a SingletonDIModule, while trying to add as SingletonDIModule")
        }

        _registeredModules.add(diModule)
    }
}

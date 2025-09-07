package com.kuxln.kuxlndi

import android.app.Application
import android.content.Context
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

object DIContainer {
    private lateinit var applicationContext: Context

    private val singletonProviderMap: MutableMap<KClass<*>, Provider<*>> = HashMap()
    private val providerMap: MutableMap<KClass<*>, Provider<*>> = HashMap()
    private val singletonInstanceMap = HashMap<KClass<*>, Any>()

    fun init(application: Application) {
        applicationContext = application.applicationContext
    }

    fun register(module: DIModule) {
        checkInitialization()

        if (module is SingletonDIModule) {
            val singletonModuleScopeImpl = SingletonModuleScopeImpl()
            module.registerDependencies(singletonModuleScopeImpl)

            singletonModuleScopeImpl.registeredDependencies.forEach { dependency ->
                singletonProviderMap[dependency.kClass] = dependency
            }

            singletonModuleScopeImpl.registeredModules.forEach { childModule ->
                register(childModule)
            }
        } else {
            val moduleScopeImpl = ModuleScopeImpl()
            module.registerDependencies(moduleScopeImpl)

            moduleScopeImpl.registeredDependencies.forEach { dependency ->
                providerMap[dependency.kClass] = dependency
            }

            moduleScopeImpl.registeredModules.forEach { childModule ->
                this.register(childModule)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getObjectInstance(kClass: KClass<T>): T {
        checkInitialization()

        if (singletonProviderMap.contains(kClass)) {
            if (!singletonInstanceMap.contains(kClass)) {
                singletonInstanceMap[kClass] = singletonProviderMap[kClass]!!.get()
            }

            return singletonInstanceMap[kClass] as T
        }

        if (providerMap.contains(kClass)) {
            return providerMap[kClass]!!.get() as T
        }

        val injectableConstructor: KFunction<Any>? = kClass.constructors.find { constructor ->
            constructor.annotations.any { annotation ->
                (annotation.annotationClass == Inject::class)
            }
        }

        if (injectableConstructor == null) {
            throw IllegalArgumentException("DI: Cannot find a constructor marked with @Injectable annotation for class ${kClass.qualifiedName}")
        }

        val args = injectableConstructor.parameters.associateWith { parameter ->
            val type = parameter.type

            val classifier = type.classifier
            getObjectInstance(classifier as KClass<*>)
        }

        return injectableConstructor.callBy(args) as T
    }

    private fun checkInitialization() {
        if (!this::applicationContext.isInitialized) {
            throw IllegalStateException("The DI container must be initialized before using at Application class")
        }
    }
}

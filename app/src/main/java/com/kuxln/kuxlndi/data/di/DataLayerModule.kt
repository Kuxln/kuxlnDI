package com.kuxln.kuxlndi.data.di

import com.kuxln.kuxlndi.ModuleScope
import com.kuxln.kuxlndi.SingletonDIModule

object DataLayerModule: SingletonDIModule {
    override fun registerDependencies(moduleScope: ModuleScope) {
        moduleScope.add(DataSourceModule)
        moduleScope.add(RepositoryModule)
    }
}
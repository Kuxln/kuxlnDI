package com.kuxln.kuxlndi.data.di

import com.kuxln.kuxlndi.ModuleScope
import com.kuxln.kuxlndi.SingletonDIModule
import com.kuxln.kuxlndi.data.datasource.DefaultStringsDataSource
import com.kuxln.kuxlndi.data.datasource.DefaultStringsListDataSource
import com.kuxln.kuxlndi.data.datasource.StringsDataSource
import com.kuxln.kuxlndi.data.datasource.StringsListDataSource
import com.kuxln.kuxlndi.provider

object DataSourceModule : SingletonDIModule {
    override fun registerDependencies(moduleScope: ModuleScope) {
        moduleScope.add(provider<StringsDataSource, DefaultStringsDataSource> { DefaultStringsDataSource() })
        moduleScope.add(provider<StringsListDataSource, DefaultStringsListDataSource> { DefaultStringsListDataSource() })
    }
}

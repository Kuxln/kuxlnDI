package com.kuxln.kuxlndi.data.di

import com.kuxln.kuxlndi.ModuleScope
import com.kuxln.kuxlndi.SingletonDIModule
import com.kuxln.kuxlndi.data.datasource.StringsDataSource
import com.kuxln.kuxlndi.data.datasource.StringsListDataSource
import com.kuxln.kuxlndi.data.repository.DefaultStringsRepository
import com.kuxln.kuxlndi.data.repository.StringsRepository
import com.kuxln.kuxlndi.di
import com.kuxln.kuxlndi.provider

object RepositoryModule : SingletonDIModule {
    override fun registerDependencies(moduleScope: ModuleScope) {
        moduleScope.add(
            provider<StringsRepository, DefaultStringsRepository> {
                val stringsDataSource: StringsDataSource by di()
                val stringsListDataSource: StringsListDataSource by di()

                DefaultStringsRepository(
                    stringsDataSource = stringsDataSource,
                    stringsListDataSource = stringsListDataSource
                )
            }
        )
    }
}

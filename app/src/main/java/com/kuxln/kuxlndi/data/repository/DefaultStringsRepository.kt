package com.kuxln.kuxlndi.data.repository

import com.kuxln.kuxlndi.Inject
import com.kuxln.kuxlndi.Singleton
import com.kuxln.kuxlndi.data.datasource.StringsDataSource
import com.kuxln.kuxlndi.data.datasource.StringsListDataSource

@Singleton
class DefaultStringsRepository @Inject constructor(
    private val stringsDataSource: StringsDataSource,
    private val stringsListDataSource: StringsListDataSource,
) : StringsRepository {
    override fun getStringExample(): String = stringsDataSource.getData()

    override fun getStringListExample(): List<String> = stringsListDataSource.getData()
}

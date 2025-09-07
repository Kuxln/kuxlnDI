package com.kuxln.kuxlndi.data.datasource

import com.kuxln.kuxlndi.Singleton

@Singleton
class DefaultStringsDataSource : StringsDataSource {
    override fun getData(): String = "Test Data"
}

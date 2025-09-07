package com.kuxln.kuxlndi.data.datasource

import com.kuxln.kuxlndi.Singleton

@Singleton
class DefaultStringsListDataSource: StringsListDataSource {
    override fun getData(): List<String> = listOf("Test string1", "Test string2", "Test string2")
}

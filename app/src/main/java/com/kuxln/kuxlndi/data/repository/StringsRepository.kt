package com.kuxln.kuxlndi.data.repository

interface StringsRepository {
    fun getStringExample() : String

    fun getStringListExample() : List<String>
}

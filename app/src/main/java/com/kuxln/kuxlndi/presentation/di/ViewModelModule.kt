package com.kuxln.kuxlndi.presentation.di

import com.kuxln.kuxlndi.DIModule
import com.kuxln.kuxlndi.ModuleScope
import com.kuxln.kuxlndi.di
import com.kuxln.kuxlndi.domain.usecase.GetStringUseCase
import com.kuxln.kuxlndi.domain.usecase.GetStringsListUseCase
import com.kuxln.kuxlndi.presentation.MainViewModel
import com.kuxln.kuxlndi.provider

object ViewModelModule : DIModule {
    override fun registerDependencies(moduleScope: ModuleScope) {
        moduleScope.add(
            provider {
                val getStringUseCase: GetStringUseCase by di()
                val getStringsListUseCase: GetStringsListUseCase by di()

                MainViewModel(
                    getStringUseCase = getStringUseCase,
                    getStringsListUseCase = getStringsListUseCase,
                )
            }
        )
    }
}
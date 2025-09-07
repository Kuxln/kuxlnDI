package com.kuxln.kuxlndi.presentation

import android.app.Application
import com.kuxln.kuxlndi.DIContainer
import com.kuxln.kuxlndi.data.di.DataLayerModule
import com.kuxln.kuxlndi.presentation.di.ViewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DIContainer.init(this)
        DIContainer.register(DataLayerModule)
        DIContainer.register(ViewModelModule)
    }
}

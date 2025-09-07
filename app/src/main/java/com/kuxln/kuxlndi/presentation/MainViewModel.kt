package com.kuxln.kuxlndi.presentation

import androidx.lifecycle.ViewModel
import com.kuxln.kuxlndi.Inject
import com.kuxln.kuxlndi.domain.usecase.GetStringUseCase
import com.kuxln.kuxlndi.domain.usecase.GetStringsListUseCase

class MainViewModel @Inject constructor(
    private val getStringUseCase: GetStringUseCase,
    private val getStringsListUseCase: GetStringsListUseCase,
) : ViewModel() {
    fun getString() = getStringUseCase()

    fun getStringsList() = getStringsListUseCase()
}

package com.kuxln.kuxlndi.domain.usecase

import com.kuxln.kuxlndi.Inject
import com.kuxln.kuxlndi.data.repository.StringsRepository

class GetStringUseCase @Inject constructor(
    private val stringsRepository: StringsRepository,
) {
    operator fun invoke() = stringsRepository.getStringExample()
}

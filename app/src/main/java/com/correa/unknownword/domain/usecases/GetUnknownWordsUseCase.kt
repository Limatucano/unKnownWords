package com.correa.unknownword.domain.usecases

import com.correa.unknownword.domain.repositories.LocalRepository
import javax.inject.Inject

interface GetUnknownWordsUseCase {
    suspend operator fun invoke(howMany: Int): List<String>
}

class GetUnknownWordsUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : GetUnknownWordsUseCase {

    override suspend fun invoke(howMany: Int): List<String> = localRepository.getUnknownWords()
        .shuffled()
        .take(howMany)
}
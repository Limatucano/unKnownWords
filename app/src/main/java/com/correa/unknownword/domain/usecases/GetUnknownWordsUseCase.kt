package com.correa.unknownword.domain.usecases

import com.correa.unknownword.domain.entities.WordEntity
import com.correa.unknownword.domain.repositories.LocalRepository
import javax.inject.Inject

interface GetUnknownWordsUseCase {
    suspend operator fun invoke(howMany: Int): List<WordEntity>
}

class GetUnknownWordsUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : GetUnknownWordsUseCase {

    override suspend fun invoke(howMany: Int): List<WordEntity> = localRepository.getUnknownWords()
        .shuffled()
        .take(howMany)
        .map {
            WordEntity(
                word = it,
            )
        }
}
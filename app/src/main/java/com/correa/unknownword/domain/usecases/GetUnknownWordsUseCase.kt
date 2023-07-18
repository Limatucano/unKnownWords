package com.correa.unknownword.domain.usecases

import com.correa.unknownword.domain.entities.WordEntity
import com.correa.unknownword.domain.repositories.LocalRepository
import javax.inject.Inject

interface GetUnknownWordsUseCase {
    suspend operator fun invoke(
        howMany: Int,
        words: List<WordEntity>
    ): List<WordEntity>
}

class GetUnknownWordsUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : GetUnknownWordsUseCase {

    override suspend fun invoke(
        howMany: Int,
        words: List<WordEntity>
    ): List<WordEntity> = localRepository.getUnknownWords()
        .shuffled()
        .filter { word ->
            !words.map { it.word }.contains(word)
        }
        .take(howMany)
        .map {
            WordEntity(
                word = it,
            )
        }
}
package com.correa.unknownword.domain.usecases

import com.correa.unknownword.domain.entities.GroupEntity
import javax.inject.Inject

interface LoadGroupsUseCase {
    suspend operator fun invoke(quantity: Int) : List<GroupEntity>
}

class LoadGroupsUseCaseImpl @Inject constructor() : LoadGroupsUseCase{

    override suspend fun invoke(quantity: Int): List<GroupEntity> {
        val groups = (1..quantity).map {
            GroupEntity(
                name = "Dupla ${it.toString().padStart(2, '0')}",
                isTurn = it == 1
            )
        }
        return groups
    }
}
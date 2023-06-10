package com.correa.unknownword.ui.game

import com.correa.unknownword.domain.entities.GroupEntity
import com.correa.unknownword.domain.entities.WordEntity

data class GameState(
    val groups: List<GroupEntity> = listOf(),
    val words: List<WordEntity> = listOf(),
    val currentGroup: GroupEntity? = null,
)

package com.correa.unknownword.ui.game

import com.correa.unknownword.domain.entities.GroupEntity
import com.correa.unknownword.domain.entities.WordEntity
import com.correa.unknownword.utils.Constants
import com.correa.unknownword.utils.Constants.formatTime

data class GameState(
    val groups: List<GroupEntity> = listOf(),
    val words: List<WordEntity> = listOf(),
    val currentGroup: GroupEntity? = null,
    val time: String = Constants.MAX_TIME_PER_ROUND.formatTime(),
    val progressTimer: Float = 1.00F,
    val isTimerPlaying: Boolean = false,
)

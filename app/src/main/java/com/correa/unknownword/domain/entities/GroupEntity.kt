package com.correa.unknownword.domain.entities

import java.io.Serializable

data class GroupEntity(
    val round: Int = 0,
    val name: String,
    val isTurn: Boolean = false,
    var words: List<WordEntity>? = null
) : Serializable
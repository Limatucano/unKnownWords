package com.correa.unknownword.domain.entities

data class GroupEntity(
    val round: Int = 0,
    val name: String,
    val isTurn: Boolean = false,
)
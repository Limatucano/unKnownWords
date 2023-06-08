package com.correa.unknownword.domain.repositories

interface LocalRepository {
    fun getUnknownWords() : List<String>
}
package com.correa.unknownword.data.repositories

import com.correa.unknownword.data.datasources.LocalDataSource
import com.correa.unknownword.domain.repositories.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {

    override fun getUnknownWords() = localDataSource.getUnknownWords()
}
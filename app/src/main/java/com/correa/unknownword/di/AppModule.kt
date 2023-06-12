package com.correa.unknownword.di

import com.correa.unknownword.data.datasources.LocalDataSource
import com.correa.unknownword.data.repositories.LocalRepositoryImpl
import com.correa.unknownword.domain.entities.GroupEntity
import com.correa.unknownword.domain.repositories.LocalRepository
import com.correa.unknownword.domain.usecases.GetUnknownWordsUseCase
import com.correa.unknownword.domain.usecases.GetUnknownWordsUseCaseImpl
import com.correa.unknownword.domain.usecases.LoadGroupsUseCase
import com.correa.unknownword.domain.usecases.LoadGroupsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideLocalDataSource() : LocalDataSource = LocalDataSource()

    @Provides
    fun provideLocalRepository(
        localDataSource: LocalDataSource
    ) : LocalRepository = LocalRepositoryImpl(
        localDataSource = localDataSource
    )

    @Provides
    fun provideGetUnknownWordsUseCase(
        localRepository: LocalRepository
    ) : GetUnknownWordsUseCase = GetUnknownWordsUseCaseImpl(
        localRepository = localRepository
    )

    @Provides
    fun provideLoadGroupsUseCase() : LoadGroupsUseCase = LoadGroupsUseCaseImpl()

    @Provides
    @Singleton
    fun provideGroupEntityList(): List<GroupEntity> = listOf()
}
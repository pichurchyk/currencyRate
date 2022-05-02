package com.pichurchyk.softCorp.di

import com.pichurchyk.data.repository.CurrenciesRepositoryImpl
import com.pichurchyk.data.source.local.LocalDataSource
import com.pichurchyk.data.source.remote.RemoteDataSource
import com.pichurchyk.domain.repository.CurrenciesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideCurrenciesRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): CurrenciesRepository = CurrenciesRepositoryImpl(remoteDataSource, localDataSource)

}
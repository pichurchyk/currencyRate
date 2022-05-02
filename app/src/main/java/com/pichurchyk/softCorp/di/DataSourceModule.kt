package com.pichurchyk.softCorp.di

import com.pichurchyk.data.source.local.LocalDataSource
import com.pichurchyk.data.source.local.room.CurrenciesDao
import com.pichurchyk.data.source.remote.RemoteDataSource
import com.pichurchyk.data.source.remote.api.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    fun provideRemoteDataSource(currencyApi: CurrencyApi): RemoteDataSource =
        RemoteDataSource(currencyApi)

    @Provides
    fun provideLocalDataSource(currencyDao: CurrenciesDao): LocalDataSource =
        LocalDataSource(currencyDao)

}
package com.pichurchyk.data.repository

import com.pichurchyk.data.source.local.LocalDataSource
import com.pichurchyk.data.source.remote.RemoteDataSource
import com.pichurchyk.domain.model.Currency
import com.pichurchyk.domain.model.CurrencyRateItem
import com.pichurchyk.domain.model.TaskResult
import com.pichurchyk.domain.repository.CurrenciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CurrenciesRepository {

    override fun getAllBaseCurrencies(): Flow<TaskResult<List<String>>> =
        remoteDataSource.getAllBaseCurrencies()

    override fun addCurrencyToFavorite(currency: CurrencyRateItem) =
        localDataSource.addCurrencyToFavorite(currency)

    override fun removeCurrencyFromFavorite(currency: CurrencyRateItem) =
        localDataSource.removeCurrencyFromFavorite(currency)

    override fun getAllLikedCurrencies(): Flow<List<CurrencyRateItem>> =
        localDataSource.getAllLikedCurrencies()

    override suspend fun getLatestCurrencies(baseCurrency: String): Currency =
        remoteDataSource.getLatestCurrencies(baseCurrency)
}
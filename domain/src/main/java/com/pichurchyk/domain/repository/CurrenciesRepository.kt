package com.pichurchyk.domain.repository

import com.pichurchyk.domain.model.Currency
import com.pichurchyk.domain.model.CurrencyRateItem
import com.pichurchyk.domain.model.TaskResult
import kotlinx.coroutines.flow.Flow

interface CurrenciesRepository {

    fun getAllBaseCurrencies(): Flow<TaskResult<List<String>>>

    fun addCurrencyToFavorite(currency: CurrencyRateItem)
    fun removeCurrencyFromFavorite(currency: CurrencyRateItem)

    fun getAllLikedCurrencies(): Flow<List<CurrencyRateItem>>

    suspend fun getLatestCurrencies(baseCurrency: String): Currency
}
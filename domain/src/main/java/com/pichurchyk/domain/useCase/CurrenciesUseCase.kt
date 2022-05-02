package com.pichurchyk.domain.useCase

import com.pichurchyk.domain.model.Currency
import com.pichurchyk.domain.model.CurrencyRateItem
import com.pichurchyk.domain.model.TaskResult
import com.pichurchyk.domain.repository.CurrenciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: CurrenciesRepository) {

    fun getAllBaseCurrencies(): Flow<TaskResult<List<String>>> = repository.getAllBaseCurrencies()

    fun addCurrencyToFavorite(currency: CurrencyRateItem) =
        repository.addCurrencyToFavorite(currency)

    fun removeCurrencyFromFavorite(currency: CurrencyRateItem) =
        repository.removeCurrencyFromFavorite(currency)

    fun getAllLikedCurrencies(): Flow<List<CurrencyRateItem>> = repository.getAllLikedCurrencies()

    suspend fun getLatestCurrencies(baseCurrency: String): Currency =
        repository.getLatestCurrencies(baseCurrency)
}
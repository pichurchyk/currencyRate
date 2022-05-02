package com.pichurchyk.data.source.local

import com.pichurchyk.data.mapper.mapToCurrencyRateItem
import com.pichurchyk.data.mapper.mapToCurrencyRateItemEntity
import com.pichurchyk.data.source.local.room.CurrenciesDao
import com.pichurchyk.domain.model.CurrencyRateItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val currenciesDao: CurrenciesDao) {

    fun addCurrencyToFavorite(currency: CurrencyRateItem) =
        currenciesDao.likeCurrency(currency.mapToCurrencyRateItemEntity())

    fun removeCurrencyFromFavorite(currency: CurrencyRateItem) =
        currenciesDao.removeLike(currency.mapToCurrencyRateItemEntity())

    fun getAllLikedCurrencies(): Flow<List<CurrencyRateItem>> = flow {
        currenciesDao.getAllLikedCurrencies().collect { list ->
            emit(list.map { likedCurrencyItem -> likedCurrencyItem.mapToCurrencyRateItem() })
        }
    }
}
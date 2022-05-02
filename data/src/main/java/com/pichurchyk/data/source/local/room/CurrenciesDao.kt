package com.pichurchyk.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun likeCurrency(currency: CurrencyRateItemEntity)

    @Delete
    fun removeLike(currency: CurrencyRateItemEntity)

    @Query("Select * FROM likedCurrencies")
    fun getAllLikedCurrencies(): Flow<List<CurrencyRateItemEntity>>

}
package com.pichurchyk.data.source.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likedCurrencies")
data class CurrencyRateItemEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val value: Double
)
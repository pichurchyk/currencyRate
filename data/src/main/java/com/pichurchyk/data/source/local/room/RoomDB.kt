package com.pichurchyk.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyRateItemEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun dao(): CurrenciesDao

}
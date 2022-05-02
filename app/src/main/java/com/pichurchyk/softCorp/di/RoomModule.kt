package com.pichurchyk.softCorp.di

import android.content.Context
import androidx.room.Room
import com.pichurchyk.data.source.local.room.CurrenciesDao
import com.pichurchyk.data.source.local.room.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): RoomDB =
        Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "FavoriteCurrenciesDB"
        ).build()

    @Provides
    fun provideDao(roomDB: RoomDB): CurrenciesDao = roomDB.dao()

}
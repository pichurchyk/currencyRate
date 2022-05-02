package com.pichurchyk.data.source.remote.api

import com.pichurchyk.domain.model.BaseCodes
import com.pichurchyk.domain.model.Currency
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {

    @GET("latest/{baseCode}")
    suspend fun getLatestCurrencies(@Path("baseCode") baseCurrency: String = "USD"): Currency

    @GET("codes")
    suspend fun getAllBaseCurrencies(): BaseCodes

}
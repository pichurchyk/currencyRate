package com.pichurchyk.data.source.remote

import com.pichurchyk.data.source.remote.api.CurrencyApi
import com.pichurchyk.domain.model.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: CurrencyApi) {

    suspend fun getLatestCurrencies(baseCurrency: String) = api.getLatestCurrencies(baseCurrency)

    fun getAllBaseCurrencies(): Flow<TaskResult<List<String>>> = flow {
        try {
            val result: List<String> = api.getAllBaseCurrencies().supportedCodes.map { it[0] }
            emit(TaskResult.Success(result))
        } catch (error: Throwable) {
            emit(TaskResult.Error(error))
        }
    }
}
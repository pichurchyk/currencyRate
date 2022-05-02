package com.pichurchyk.softCorp.ui.mainScreen.likedCurrencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pichurchyk.data.dto.CurrencyRateItemDto
import com.pichurchyk.data.mapper.getRates
import com.pichurchyk.data.mapper.mapToCurrencyRateItem
import com.pichurchyk.data.mapper.mapToCurrencyRateItemDto
import com.pichurchyk.domain.model.TaskResult
import com.pichurchyk.domain.useCase.CurrenciesUseCase
import com.pichurchyk.softCorp.common.constants.SortConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedCurrenciesViewModel @Inject constructor(private val useCase: CurrenciesUseCase) :
    ViewModel() {

    private val _currencies = MutableSharedFlow<List<CurrencyRateItemDto>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val currencies = _currencies.asSharedFlow()

    private val _baseCodes =
        MutableSharedFlow<List<String>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val baseCodes = _baseCodes.asSharedFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private var currenciesFlow: Job? = null

    fun getCurrenciesList(baseCode: String = "USD") {
        currenciesFlow?.cancel()

        val latestCurrencies = viewModelScope.async {
            return@async try {
                TaskResult.Success(useCase.getLatestCurrencies(baseCode))
            } catch (error: Throwable) {
                TaskResult.Error(error)
            }
        }

        currenciesFlow = viewModelScope.launch {
            when (val apiCall = latestCurrencies.await()) {
                is TaskResult.Success -> {
                    useCase.getAllLikedCurrencies().collectLatest { resultFromDb ->
                        val resultFromDbNames = resultFromDb.map { it.name }

                        _currencies.emit(apiCall.value.getRates()
                            .filter { resultFromDbNames.contains(it.name) }
                            .map { it.mapToCurrencyRateItemDto().apply { isLiked = true } })
                    }
                }
                is TaskResult.Error -> _error.value = apiCall.throwable.message
            }
        }
    }

    fun getAllBaseCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllBaseCurrencies().collectLatest { result ->
                when (result) {
                    is TaskResult.Success -> _baseCodes.emit(result.value)
                    is TaskResult.Error -> _error.value = result.throwable.message
                }
            }
        }
    }

    fun removeCurrencyFromFavorite(currencyRateItem: CurrencyRateItemDto) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.removeCurrencyFromFavorite(currencyRateItem.mapToCurrencyRateItem())
        }
    }

    fun sortList(sortType: SortConstants) {
        viewModelScope.launch {
            val arrayToSort = currencies.first()
            when (sortType) {
                SortConstants.AZ -> _currencies.emit(arrayToSort.sortedBy { it.name })
                SortConstants.ZA -> _currencies.emit(arrayToSort.sortedByDescending { it.name })
                SortConstants.LOWEST -> _currencies.emit(arrayToSort.sortedBy { it.value })
                SortConstants.HIGHEST -> _currencies.emit(arrayToSort.sortedByDescending { it.value })
            }
        }
    }
}
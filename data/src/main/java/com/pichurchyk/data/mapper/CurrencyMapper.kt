package com.pichurchyk.data.mapper

import com.pichurchyk.data.dto.CurrencyRateItemDto
import com.pichurchyk.data.source.local.room.CurrencyRateItemEntity
import com.pichurchyk.domain.model.CurrencyRateItem
import com.pichurchyk.domain.model.Currency

fun Currency.getRates() : List<CurrencyRateItem> {
    val listOfRates = mutableListOf<CurrencyRateItem>()
    this.conversionRates.forEach { rate ->
        listOfRates.add(CurrencyRateItem(name = rate.key, value = rate.value))
    }
    return listOfRates
}

fun CurrencyRateItem.mapToCurrencyRateItemEntity() : CurrencyRateItemEntity = CurrencyRateItemEntity(
    name = this.name,
    value = this.value,
)

fun CurrencyRateItem.mapToCurrencyRateItemDto() : CurrencyRateItemDto = CurrencyRateItemDto(
    name = this.name,
    value = this.value,
    isLiked = false,
)

fun CurrencyRateItemEntity.mapToCurrencyRateItem() : CurrencyRateItem = CurrencyRateItem(
    name = this.name,
    value = this.value,
)

fun CurrencyRateItemDto.mapToCurrencyRateItem() : CurrencyRateItem = CurrencyRateItem(
    name = this.name,
    value = this.value,
)
package com.pichurchyk.domain.model


import com.google.gson.annotations.SerializedName

data class BaseCodes(
    @SerializedName("supported_codes")
    val supportedCodes: List<List<String>>,
)
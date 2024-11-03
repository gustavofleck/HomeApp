package com.example.homeapp.common.model

import com.google.gson.annotations.SerializedName

internal data class TextFieldResponse(
    @SerializedName("stringValue") val value: String? = null
)

internal data class BooleanFieldResponse(
    @SerializedName("booleanValue") val value: Boolean? = null
)
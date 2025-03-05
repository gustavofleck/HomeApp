package com.example.homeapp.features.controlpanel.data.model

import com.example.homeapp.common.model.CollectionItemResponse
import com.example.homeapp.features.controlpanel.domain.model.ControlPanelData
import com.google.gson.annotations.SerializedName

internal data class ControlPanelFieldsResponse(
    @SerializedName("icon") val icon: String? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("deeplink") val deeplink: String? = null
)

internal fun CollectionItemResponse<ControlPanelFieldsResponse>.toDomain() = ControlPanelData(
    id = name?.split("/")?.last().orEmpty(),
    text = fields?.text.orEmpty(),
    icon = fields?.icon.orEmpty(),
    deeplink = fields?.deeplink.orEmpty()
)



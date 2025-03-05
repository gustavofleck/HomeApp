package com.example.homeapp.common.model

import com.google.gson.annotations.SerializedName

internal data class FirestoreCollectionResponse<T>(
    @SerializedName("documents") val documents: List<CollectionItemResponse<T>>? = null
)

internal data class CollectionItemResponse<T>(
    @SerializedName("name") val name: String? = null,
    @SerializedName("fields") val fields: T? = null,
    @SerializedName("createTime") val createTime: String? = null,
    @SerializedName("updateTime") val updateTime: String? = null
)
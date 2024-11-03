package com.example.homeapp.annotations.data.model

import com.example.homeapp.annotations.domain.model.Note
import com.example.homeapp.common.model.BooleanFieldResponse
import com.example.homeapp.common.model.TextFieldResponse
import com.google.gson.annotations.SerializedName

internal data class NoteResponseList(
    @SerializedName("documents") val documents: List<NoteResponse>? = null
)

internal data class NoteResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("fields") val fields: AnnotationFieldsResponse? = null,
    @SerializedName("createTime") val createTime: String? = null,
    @SerializedName("updateTime") val updateTime: String? = null
)

internal data class AnnotationFieldsResponse(
    @SerializedName("text") val text: TextFieldResponse? = null,
    @SerializedName("favorite") val favorite: BooleanFieldResponse? = null
)

internal fun NoteResponse.toDomain() = Note(
    id = name?.split("/")?.last().orEmpty(),
    text = fields?.text?.value.orEmpty(),
    favorite = fields?.favorite?.value ?: false,
    created = createTime.orEmpty(),
    updated = updateTime.orEmpty()
)

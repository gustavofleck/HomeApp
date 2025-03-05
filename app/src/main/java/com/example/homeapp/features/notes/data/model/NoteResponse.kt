package com.example.homeapp.features.notes.data.model

import com.example.homeapp.features.notes.domain.model.Note
import com.example.homeapp.common.model.BooleanFieldResponse
import com.example.homeapp.common.model.CollectionItemResponse
import com.example.homeapp.common.model.FirestoreCollectionResponse
import com.example.homeapp.common.model.TextFieldResponse
import com.google.gson.annotations.SerializedName

internal data class NoteFieldsResponse(
    @SerializedName("text") val text: TextFieldResponse? = null,
    @SerializedName("favorite") val favorite: BooleanFieldResponse? = null
)

internal fun CollectionItemResponse<NoteFieldsResponse>.toDomain() = Note(
    id = name?.split("/")?.last().orEmpty(),
    text = fields?.text?.value.orEmpty(),
    favorite = fields?.favorite?.value ?: false,
    created = createTime.orEmpty(),
    updated = updateTime.orEmpty()
)

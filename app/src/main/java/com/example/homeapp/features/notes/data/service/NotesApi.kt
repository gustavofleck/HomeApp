package com.example.homeapp.features.notes.data.service

import com.example.homeapp.common.model.FirestoreCollectionResponse
import com.example.homeapp.features.notes.data.model.NoteFieldsResponse
import com.example.homeapp.features.notes.data.model.NoteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface NotesApi {

    @GET("notes")
    suspend fun notes() : FirestoreCollectionResponse<NoteFieldsResponse>

    @POST("notes")
    suspend fun addNote(@Body note: NoteRequest)
}
package com.example.homeapp.notes.data.service

import com.example.homeapp.notes.data.model.NoteRequest
import com.example.homeapp.notes.data.model.NoteResponseList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface NotesApi {

    @GET("notes")
    suspend fun notes() : NoteResponseList

    @POST("notes")
    suspend fun addNote(@Body note: NoteRequest)
}
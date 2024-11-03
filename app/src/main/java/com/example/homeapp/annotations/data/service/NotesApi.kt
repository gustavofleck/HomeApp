package com.example.homeapp.annotations.data.service

import com.example.homeapp.annotations.data.model.NoteResponseList
import retrofit2.http.GET

internal interface NotesApi {

    @GET("/notes")
    suspend fun notes() : NoteResponseList
}
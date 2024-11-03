package com.example.homeapp.notes.data.service

import com.example.homeapp.notes.data.model.NoteResponseList
import retrofit2.http.GET

internal interface NotesApi {

    @GET("/notes")
    suspend fun notes() : NoteResponseList
}
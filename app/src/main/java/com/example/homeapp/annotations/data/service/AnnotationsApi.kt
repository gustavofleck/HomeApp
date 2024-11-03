package com.example.homeapp.annotations.data.service

import com.example.homeapp.annotations.data.model.AnnotationResponseList
import retrofit2.http.GET

internal interface AnnotationsApi {

    @GET("/annotations")
    suspend fun annotations() : AnnotationResponseList
}
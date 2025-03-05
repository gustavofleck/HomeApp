package com.example.homeapp.features.controlpanel.data.service

import com.example.homeapp.common.model.FirestoreCollectionResponse
import com.example.homeapp.features.controlpanel.data.model.ControlPanelFieldsResponse
import retrofit2.http.GET

internal interface ControlPanelApi {

    @GET("/control_panel")
    fun controlPanel(): FirestoreCollectionResponse<ControlPanelFieldsResponse>

}
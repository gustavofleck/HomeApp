package com.example.homeapp.features.controlpanel.data.datasource

import com.example.homeapp.features.controlpanel.data.model.toDomain
import com.example.homeapp.features.controlpanel.data.service.ControlPanelApi
import com.example.homeapp.features.controlpanel.domain.model.ControlPanelData

internal class ControlPanelDataSource(
    private val api: ControlPanelApi
) {

    fun controlPanel(): List<ControlPanelData> = api.controlPanel().documents?.map { item ->
        item.toDomain()
    }.orEmpty()

}
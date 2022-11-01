package com.example.listaeventos.service.listener

import com.example.listaeventos.service.model.EventsModel

interface EventListener {
    fun onClickEvent(event: EventsModel)
}
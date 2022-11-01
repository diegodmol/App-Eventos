package com.example.listaeventos.service.repository

import com.example.listaeventos.service.model.EventsModel
import com.example.listaeventos.service.model.ReturnModel
import retrofit2.Call
import retrofit2.http.*

interface EventsService {

    @GET("/api/events")
    fun getEvents() : Call<List<EventsModel>>

    @POST("/api/checkin")
    @FormUrlEncoded
    fun postCheckin(@Field("eventId") eventId : String, @Field("name") name : String, @Field("email") email : String) : Call<ReturnModel>
}

